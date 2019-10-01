# Backend Pull Request Template

## Description

Please describe the issue/problem that is encountered and summary of the changes/improvements. 

Fixes # (issue)

## Type of change

Please delete options that are not relevant.

- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] This change requires a documentation update

##Changes

- Item 1
- Item 2
- Item 3

## Requests / Responses

**Request**

POST `/api/users` Returns a list of users

```
{
  "data": {
    "attributes": {
      "name": "The Dude",
      "email": "thedudeabides@wee.net",
      "password": "hellopassword"
    }
  },
  "type": "users"
}
```

**Response**

HTTP/1.1 200 OK

```
{
  "data": {
    "type": "users",
    "id": "4",
    "attributes": {
      "name": "The Dude",
      "email": "thedudeabides@wee.net",
      "last-logged-in": null,
      "created-at": "2016-10-20T17:45:08.190Z",
      "updated-at": "2016-10-20T17:45:08.190Z"
    },
    "links": {
      "self": "/users/4"
    }
  }
}
```

## Testing

- [ ] Deploy to staging
- [ ] Run migrations
- [ ] Determine production deploy timing



## Comments

Any additional information...
