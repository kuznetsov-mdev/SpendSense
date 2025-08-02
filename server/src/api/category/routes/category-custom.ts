/**
 * category custom router
 */

import { factories } from '@strapi/strapi';

export default {
 routes: [
        {
            method: 'POST',
            path: '/categories/sync',
            handler: 'category.sync'
        }
    ]
};