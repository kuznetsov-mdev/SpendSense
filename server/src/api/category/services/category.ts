/**
 * category service
 */

import { factories } from '@strapi/strapi';


export default factories.createCoreService('api::category.category', 
    ({ strapi }) =>  ({
        //custom
        async sync() {
            const ctx = strapi.requestContext.get();
            //get user and request body
            const user = ctx.state.user
            const { body } = ctx.request
            const type = 'api::category.category'


            // 1. get all user's categories from DB
            const categoriesFromDb = await strapi.db.query(type).findMany({
                filters: {
                    userId: user.id
                }
            })

            // 2. filter categories that already exist in DB with later creation date
            const categoriesFromClient = body.filter( (category) => 
                dictCategoriesFromDb[category.idLocal] 
                    ? dictCategoriesFromDb[category.idLocal].updatedAtLocal < category.updatedAtLocal //if category from client newer than in DB, it needs to update it in DB
                    : true //there isn't the category in DB(need to add) 
            ).map(category => {
                category.userId = user.id //add user id to categories from client
                return category
            })

            // 3. create categories map key = category.id value = category
            const dictCategoriesFromDb = Object.fromEntries(categoriesFromDb.map(
                (c) => [c.idLocal, c]
            ))

            // 4. separate categories to two groups 1st - for adding, 2nd - for updating
            const categoriesToCreate = categoriesFromClient.filter(category =>
                !dictCategoriesFromDb[category.idLocal]
            )

            const categoriesToUpdate = categoriesFromClient.filter(category =>
                dictCategoriesFromDb[category.idLocal]
            )

            // 5. save and update data on the server
            if(categoriesToCreate.length > 0) {
                await strapi.db.query(type).createMany({data: categoriesToCreate})
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
                    data: categoriesToUpdate
                })
            }

            // 6. return to client categories wich need to update on client (They are absent or updated later)
            const dictCategoriesFromClient = Object.fromEntries(body.map(c => [c.idLocal, c]))
            return categoriesFromDb
                .filter(category => dictCategoriesFromDb[category.idLocal]
                    ? dictCategoriesFromClient[category.idLocal].updatedAtLocal < category.updatedAtLocal
                    : true
                )
        },

        //default
        async create() {
            //get context
            const ctx = strapi.requestContext.get();
            //get current user
            const user = ctx.state.user
            //get request body (create new category request)
            const {body} = ctx.request
            //get category from request body
            const category = body.data
            //add userId to category
            category.userId = user.id

            //call mehtod for category creation
            await strapi.documents('api::category.category').create({
                data: category
            })

            return category;
        }
    })

);
