/**
 * category controller
 */

import { factories } from '@strapi/strapi'

export default factories.createCoreController('api::category.category', 
  ({ strapi }) =>  ({
    //default
    async find(ctx) {
      return await strapi.documents('api::category.category').findMany({
        filters: {
          userId: ctx.state.user.id
        }
      })
    },

    async create(ctx){
      const newCategory = await strapi.service('api::category.category').create()
      const sanCategory = await this.sanitizeOutput(newCategory, ctx)
      ctx.body = sanCategory
    },

    //custom
    async sync(ctx) {
        return await strapi.service('api::category.category').sync(ctx)
    }
    
  })
);