# Test Template
## Hibernate Issue HHH-12119
Linked here:  
https://hibernate.atlassian.net/browse/HHH-12119

## Database Dump:
Please use the attached database dump file (rechnung in the folder resources - postgresql format) to setup your database.
Test data is included.

## Test:
Please run the test.
The following query should be executed:  
`select i.invoice_id as "invoiceId", i.booking_tag as "bookingTag", i.invoice_number as "invoiceNumber", i.created_by as "createdBy", i.invoice_api_groups as "apiGroups", i.invoice_date as "invoiceDate", r.recipient as "recipient" from invoice i inner join recipient r on (i.recipient_recipient_id = r.recipient_id) where i.invoice_state = 0 and i.invoice_api_groups similar to '%tlang%|%Seminare%' order by i.invoice_date desc`

## Result:
The query gets executed the right way and yields some results.  
With Spring Data JPA i want to use a projection interface.  
As you have stated to explicitly not use Spring, the test does not  
show the issue in the correct manner.

However the mentioned interface is integrated in the project - see `InvoiceProjection`