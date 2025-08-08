/**
 * spend-event service
 */

import { factories } from '@strapi/strapi';

export default factories.createCoreService('api::spend-event.spend-event', 
    ({ strapi }) =>  ({
        //custom
        async sync(params) {
            const { userId, spendEvents } = params;
            const type = 'api::spend-event.spend-event';

            // 1. get all user's categories from DB
            const spendEventsFromDb = await strapi.db.query(type)
                .findMany({
                    filters: {
                        userId: userId
                    }
                })

            // 2. create categories map key = spendEvent.id value = spendEvent
            const dictSpendEventsFromDb = Object.fromEntries(spendEventsFromDb.map(
                c => [c.idLocal, c]
            ))

            // 3. filter categories that already exist in DB with later creation date
            //add user id to each spendEvent before save it into DB
            const spendEventsFromClient = spendEvents.filter( spendEvent =>
                dictSpendEventsFromDb[spendEvent.idLocal] ?
                dictSpendEventsFromDb[spendEvent.idLocal].updatedAtLocal < spendEvent.updatedAtLocal : true
            )

            // 4. separate categories to two groups 1st - for adding, 2nd - for updating
            const spendEventsToCreate = spendEventsFromClient.filter (spendEvent =>
                !dictSpendEventsFromDb[spendEvent.idLocal]
            )

            const spendEventsToUpdate =  spendEventsFromClient.filter (spendEvent =>
                dictSpendEventsFromDb[spendEvent.idLocal]
            )

            // 5. save and update data on the server
            if(spendEventsToCreate.length > 0){
                await strapi.db.query(type).createMany({ data: spendEventsToCreate })
            }

            if(spendEventsToUpdate.length > 0) {
                //delete all categories with the same idLocal
                await strapi.db.query(type).deleteMany({
                    where: {
                        idLocal: {
                            $in: spendEventsToUpdate.map(c => c.idLocal)
                        }
                    }
                })
                //insert new data
                await strapi.db.query(type).createMany({
                    data : spendEventsToUpdate
                })
            }

            // 6. return to client categories which need to update on client (They are absent or updated later)
            const dictSpendEventsFromClient = Object.fromEntries(spendEvents.map(c => [c.idLocal, c]))
            
            return spendEventsFromDb
                .filter(spendEvent => dictSpendEventsFromClient[spendEvent.idLocal] ?
                    dictSpendEventsFromClient[spendEvent.idLocal].updatedAtLocal < spendEvent.updatedAtLocal : true)
        },

        //default
        async create(params) {
            const { data, files } = params;

            if (!data?.userId) {
                throw new Error('Missing userId in data');
            }

            const created = await strapi.db.query('api::spend-event.spend-event').create({
                data,
            });

            return created;
        }   
    })

);













// export default factories.createCoreService('api::spend-event.spend-event');

// const { createCoreService } = require('@strapi/strapi').factories;

// module.exports = createCoreService('api::spend-event.spend-event', ({ strapi }) =>  ({

//     //custom
//     async sync(ctx) {
//         const user = ctx.state.user
//         const { body } = ctx.request
//         const type = 'api::spend-event.spend-event'

//         const eventsFromDb = await strapi.db.query(type)
//             .findMany({
//                 filters: {
//                     userId: user.id
//                 }
//             })

//         const dictEventsFromDb = Object.fromEntries(eventsFromDb.map(
//             c => [c.idLocal, c]
//         ))

//         const eventsFromClient = body.filter( event =>
//             dictEventsFromDb[event.idLocal] ?
//             dictEventsFromDb[event.idLocal].updatedAtLocal < event.updatedAtLocal : true
//         ).map( event => {
//             event.userId = user.id
//             return event
//         })

//         const eventsToCreate = eventsFromClient.filter ( event =>
//             !dictEventsFromDb[event.idLocal]
//         )

//         const eventsToUpdate =  eventsFromClient.filter ( event =>
//             dictEventsFromDb[event.idLocal]
//         )

//         if(eventsToCreate.length > 0){
//             await strapi.db.query(type).createMany({ data: eventsToCreate })
//         }

//         if(eventsToUpdate.length > 0){
//             await strapi.db.query(type).deleteMany({
//                 where: {
//                     idLocal: {
//                         $in: eventsToUpdate.map(c => c.idLocal)
//                     }
//                 }
//             })
//             await strapi.db.query(type).createMany({
//                 data : eventsToUpdate
//             })
//         }

//         const dictEventsFromClient = Object.fromEntries(body.map(c => [c.idLocal, c]))
//         return eventsFromDb
//             .filter(event => dictEventsFromClient[event.idLocal] ?
//                 dictEventsFromClient[event.idLocal].updatedAtLocal < event.updatedAtLocal : true)

//     },
//   }));