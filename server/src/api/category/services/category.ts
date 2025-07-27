/**
 * category service
 */

import { factories } from '@strapi/strapi';


export default factories.createCoreService('api::category.category', 
    ({ strapi }) =>  ({
        //custom
        async exampleAction(...args) {
            return null;
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
