%dw 2.0
output application/json
---
if (payload == "200") "" else {message: payload}