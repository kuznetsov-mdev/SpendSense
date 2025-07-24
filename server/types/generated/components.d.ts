import type { Schema, Struct } from '@strapi/strapi';

export interface TimestampsDatetime extends Struct.ComponentSchema {
  collectionName: 'components_timestamps_datetimes';
  info: {
    displayName: 'Datetime';
    icon: 'clock';
  };
  attributes: {
    createdAtLocal: Schema.Attribute.DateTime;
    updatedAtLocal: Schema.Attribute.DateTime;
  };
}

declare module '@strapi/strapi' {
  export module Public {
    export interface ComponentSchemas {
      'timestamps.datetime': TimestampsDatetime;
    }
  }
}
