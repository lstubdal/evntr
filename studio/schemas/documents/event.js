export default {
    title: 'Event',
    name: 'event',
    type: 'document',
    fields: [
        {
            title: 'Title',
            name: 'title',
            type: 'string',
            validation: Rule => [ 
                Rule.required().min(5).error('The title needs atleast 5 characters'),
                Rule.max(99).error('Titles with more than 99 characters are too long')
            ]
        }, 
        {
            title: 'Event image',
            name: 'eventImage',
            type: 'image',
            validation: Rule => Rule.required()
        },

        {
            title: 'Time',
            name: 'time',
            type: 'datetime',
            validation: Rule => Rule.required().min(new Date())
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
            }],
            validation: Rule => Rule.required()
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