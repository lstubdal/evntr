import createSchema from 'part:@sanity/base/schema-creator'
import schemaTypes from 'all:part:@sanity/base/schema-type'

import event from './documents/event.js'
import address from './documents/address.js'
import host from './documents/host.js'
import category from './documents/category.js'
import speaker from './documents/speaker.js'
import attendee from './documents/attendee.js'

export default createSchema({
  name: 'default',
  types: schemaTypes.concat([
    event,
    address,
    host,
    category,
    speaker,
    attendee
  ]),
})
