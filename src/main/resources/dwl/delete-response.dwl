%dw 2.0
output application/json
---
{message: if (payload == "200") "Record deleted successfully" else payload}