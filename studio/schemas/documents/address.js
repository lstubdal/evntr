export default {
    type: 'Address',
    name: 'address',
    type: 'document',
    fields: [
        {
            title: 'Street Address',
            name: 'streetAddress',
            type: 'string',
            validation: Rule => [ 
                Rule.required().min(10).error('The title needs atleast 10 character'),
            ]
        },
        {
            title: 'City',
            name: 'city',
            type: 'string',
            validation: Rule => [ 
                Rule.required().min(1).error('The title needs atleast 20 character'),
            ]
        }
    ]
}