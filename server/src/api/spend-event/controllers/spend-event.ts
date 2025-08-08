/**
 * spend-event controller
 */

import { factories } from '@strapi/strapi'

export default factories.createCoreController('api::spend-event.spend-event');

'use strict';

/**
 * spend-event controller
 */

const { createCoreController } = require('@strapi/strapi').factories;

module.exports = createCoreController('api::spend-event.spend-event',
({ strapi }) => ({

     //default
     async find(ctx) {
        return await strapi.documents('api::spend-event.spend-event').findMany({
          filters: {
            userId: ctx.state.user.id
          }
        })
      },

      async create(ctx){
        const user = ctx.state.user;
        const { data, files } = ctx.request.body;
  
        const params = {
          data: {
            ...data,
            userId: user.id,
          },
          files,
        };
  
        const newSpendEvent = await strapi
          .service('api::spend-event.spend-event')
          .create(params)
  
        const sanCategory = await strapi.controller('api::spend-event.spend-event').sanitizeOutput(newSpendEvent, ctx)
        ctx.body = sanCategory
      },

     //custom
     async sync(ctx) {
        const userId = ctx.state.user.id;
        const spendEvents = ctx.request.body;
      
        return await strapi
          .service('api::spend-event.spend-event')
          .sync({ userId, spendEvents });
      }
})
);