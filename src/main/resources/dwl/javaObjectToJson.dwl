%dw 2.0
input payload application/java
output application/json
---
if (payload.response != "200") {message: payload.response} else
{
	fname: payload.fname,
	lname: payload.lname,
	gender: payload.gender,
	id: payload.id,
	age: payload.age
}