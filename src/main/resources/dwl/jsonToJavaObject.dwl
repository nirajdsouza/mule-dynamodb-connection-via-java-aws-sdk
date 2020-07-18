%dw 2.0
output application/java
---
{
	age: payload.age,
	fname: payload.fname,
	gender: payload.gender,
	id: payload.id,
	lname: payload.lname
} as Object {
	class : "aws.dynamodb.model.DBRecord"
}