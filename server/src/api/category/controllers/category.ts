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
    async sync() {
        const ctx = strapi.requestContext.get();
        ctx.body = "data was sync"
    }, 

    async create(){
      const ctx = strapi.requestContext.get();
      const newCategory = await strapi.service('api::category.category').create()
      ctx.body = newCategory
    }
  })
);