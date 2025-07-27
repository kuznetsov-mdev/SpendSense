/**
 * category custom router
 */

import { factories } from '@strapi/strapi';

export default {
 routes: [
        {
            method: 'GET',
            path: '/categories/sync',
            handler: 'category.sync'
        }
    ]
};