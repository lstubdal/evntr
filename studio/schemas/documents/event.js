export default {
    title: 'Event',
    name: 'event',
    type: 'document',
    fields: [
        {
            title: 'Title',
            name: 'title',
            type: 'string'
        }, 
        {
            title: 'Event image',
            name: 'eventImage',
            type: 'image'
        },
        {
            title: 'Time',
            name: 'time',
            type: 'datetime'
        },
        {
            title: 'Location',
            name: 'location',
            type: 'reference',
            to: [{ type: 'location' }]
        },
        {
            title: 'Host',
            name: 'host',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'host' }]
            }]  
        },
        {
            title: 'Speaker',
            name: 'speaker',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'speaker'}]
            }]
        },
        {
            title: 'Description',
            name: 'description',
            type: 'text',
        },
        {
            title: 'Category',
            name: 'category',
            type: 'reference',
            to: [{ type: 'category'}]
        },
        {
            title: 'Price',
            name: 'price',
            type: 'number'
        },
        {
            title: 'Digital event',
            name: 'digitalEvent',
            type: 'boolean',
            description: 'Swipe right if the event is digital'
        }
    ]
}