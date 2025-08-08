'use strict';

module.exports = {
    routes: [
        {
            method: 'POST',
            path: '/spend-event/sync',
            handler: 'spend-event.sync'
        }
    ]
};