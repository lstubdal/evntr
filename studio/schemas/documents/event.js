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
                Rule.required().min(5).error('The title needs atleast 1 character'),
                Rule.max(60).error('Titles with more than 60 characters are too long')
            ]
        }, 
        {
            title: 'Event image',
            name: 'eventImage',
            type: 'image',
            validation: Rule => Rule.required().error('Event must have coverimage'),
        },
        {
            title: 'Time',
            name: 'time',
            type: 'datetime',
            validation: Rule => Rule.required().min(new Date())
        },
        {
            title: 'Host',
            name: 'host',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'host' }]
            }],
            validation: Rule => Rule.required().error('Must fill inn host')
        },
        {
            title: 'Speaker',
            name: 'speaker',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'speaker'}]
            }],
            validation: Rule => Rule.required().error('Must fill speaker')
        },
        {
            title: 'Description',
            name: 'description',
            type: 'text',
            validation: Rule => [ 
                Rule.required().min(20).error('The title needs atleast 20 character'),
                Rule.max(1000).error('Titles with more than 500 characters are too long')
            ]
        },
        {
            title: 'Category',
            name: 'category',
            type: 'reference',
            to: [{ type: 'category'}],
            validation: Rule => Rule.required().error('Need to define category')
        },
        {
            title: 'Price',
            name: 'price',
            type: 'object',
            fields: [
                {
                    title: 'Free',
                    name: 'free',
                    type: 'boolean',
                    validation: Rule => Rule.required().error('Must define price')
                },
                {
                    title: 'Price amount',
                    name : 'amount',
                    type: 'number',
                    hidden: ({ parent, boolean }) => !boolean && parent?.free,
                    validation: Rule => Rule.required().error('Need to fill in price')
                }     
            ]
        },
        {
            title: 'Location',
            name: 'location',
            type: 'object',
            fields: [
                {
                    title: 'Digital event',
                    name: 'digitalEvent',
                    type:'boolean',
                    description: 'Swipe right if the event is digital',
                    validation: Rule => Rule.required().error('Need to define event type')
                },
                {
                    title: 'Address',
                    name: 'address',
                    type: 'reference',
                    to: [{ type: 'address' }],
                    hidden: ({ parent, boolean}) => !boolean && parent?.digitalEvent,
                    validation: Rule => Rule.required().error('Need location if not digital')
                }
            ]
        },
        {
            title: 'Attendees',
            name: 'attendees',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'attendee'}] 
            }],
            readOnly: ({ currentUser }) => {
                return !(currentUser.roles.find(({name}) => name === 'viewer'));
            }
            
    /*        {
            title: 'Slug',
            name: 'slug',
            type: 'slug',
            options: {
                source: 'title',
                slugify: input => input
                                    .toLowerCase()
                                    .replace(/\s+/g, '-')
                                    .slice(0, 200)
            }
        }, */
           
        },
    ]
}