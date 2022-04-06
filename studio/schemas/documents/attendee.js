export default {
    title: 'Attendee',
    name: 'attendee',
    type: 'document',
    fields: [
        {
            title: 'Attendee mail',
            name: 'attendee',
            type: 'string'
        },
        {
            title: 'Events',
            name: 'events',
            type: 'array',
            of: [{
                type: 'reference',
                to: [{ type: 'event'}] // finne event id?
            }],
        }
    ]
}