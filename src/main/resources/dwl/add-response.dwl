%dw 2.0
output application/json
---
{message: if (payload == "200") "Record added successfully" else payload}