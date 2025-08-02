/**
 * category service
 */

import { factories } from '@strapi/strapi';


export default factories.createCoreService('api::category.category', 
    ({ strapi }) =>  ({
        //custom
        async sync(params) {
            const { userId, categories } = params;
            const type = 'api::category.category';

            // 1. get all user's categories from DB
            const categoriesFromDb = await strapi.db.query(type)
                .findMany({
                    filters: {
                        userId: userId
                    }
                })

            // 2. create categories map key = category.id value = category
            const dictCategoriesFromDb = Object.fromEntries(categoriesFromDb.map(
                c => [c.idLocal, c]
            ))

            // 3. filter categories that already exist in DB with later creation date
            //add user id to each category before save it into DB
            const categoriesFromClient = categories.filter( category =>
                dictCategoriesFromDb[category.idLocal] ?
                dictCategoriesFromDb[category.idLocal].updatedAtLocal < category.updatedAtLocal : true
            )

            // 4. separate categories to two groups 1st - for adding, 2nd - for updating
            const categoriesToCreate = categoriesFromClient.filter (category =>
                !dictCategoriesFromDb[category.idLocal]
            )

            const categoriesToUpdate =  categoriesFromClient.filter (category =>
                dictCategoriesFromDb[category.idLocal]
            )

            // 5. save and update data on the server
            if(categoriesToCreate.length > 0){
                await strapi.db.query(type).createMany({ data: categoriesToCreate })
            }

            if(categoriesToUpdate.length > 0) {
                //delete all categories with the same idLocal
                await strapi.db.query(type).deleteMany({
                    where: {
                        idLocal: {
                            $in: categoriesToUpdate.map(c => c.idLocal)
                        }
                    }
                })
                //insert new data
                await strapi.db.query(type).createMany({
                    data : categoriesToUpdate
                })
            }

            // 6. return to client categories which need to update on client (They are absent or updated later)
            const dictCategoriesFromClient = Object.fromEntries(categories.map(c => [c.idLocal, c]))
            
            return categoriesFromDb
                .filter(category => dictCategoriesFromClient[category.idLocal] ?
                    dictCategoriesFromClient[category.idLocal].updatedAtLocal < category.updatedAtLocal : true)
        },

        //default
        async create(params) {
            const { data, files } = params;

            if (!data?.userId) {
                throw new Error('Missing userId in data');
            }

            const created = await strapi.db.query('api::category.category').create({
                data,
            });

            return created;
        }   
    })

);
