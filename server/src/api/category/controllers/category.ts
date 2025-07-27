/**
 * category controller
 */

import { factories } from '@strapi/strapi'

export default factories.createCoreController('api::category.category', 
  ({ strapi }) =>  ({
    //default
    async find(ctx) {
      return null;
    },

    //custom
    async sync(ctx) {
        ctx.body = "data was sync"
    }
  })
);