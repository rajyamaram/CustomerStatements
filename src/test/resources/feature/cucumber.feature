Feature: Testing Statement-Processor REST API
    Users should be able to POST batch of transactions to Statement-Processor web service for validation

    Scenario Outline: Post set of transactions for validation
        When user uploads set of transaction records allTransactions <allTransactions> transactionsList <transactionsList>
        Then the server should validate and send back response <response> httpStatusCode <httpStatusCode>

        Examples:
        | allTransactions                                    | transactionsList                                                                                                                                                                                                                                                                              | response                                                                                                                                        | httpStatusCode |
        | Successful                                         | [{"transactionReference": "3","accountNumber": "3","startBalance": "300.00","mutation": "+600.00","Description": "AAA","endBalance": "900.00"},{"transactionReference": "4","accountNumber": "4","startBalance": "200.00","mutation": "+400.00","Description": "AAA","endBalance": "600.00"}] | {"result":"SUCCESSFUL","errorRecords":[]}                                                                                                       | 200            |
        | Duplicate_Reference                                | [{"transactionReference": "3","accountNumber": "3","startBalance": "400.00","mutation": "-100.00","Description": "AAA","endBalance": "300.00"},{"transactionReference": "3","accountNumber": "4","startBalance": "200.00","mutation": "+400.00","Description": "AAA","endBalance": "600.00"}] | {"result":"DUPLICATE_REFERENCE","errorRecords":[{"reference":3,"accountNumber":"4"}]}                                                           | 200            |
        | Incorrect_End_Balance                              | [{"transactionReference": "3","accountNumber": "3","startBalance": "300.00","mutation": "-100.00","Description": "AAA","endBalance": "300.00"},{"transactionReference": "4","accountNumber": "4","startBalance": "200.00","mutation": "+400.00","Description": "AAA","endBalance": "600.00"}] | {"result":"INCORRECT_END_BALANCE","errorRecords":[{"reference":3,"accountNumber":"3"}]}                                                         | 200            |
        | Duplicate_Reference_Incorrect_End_Balance          | [{"transactionReference": "3","accountNumber": "3","startBalance": "300.00","mutation": "+600.00","Description": "AAA","endBalance": "800.00"},{"transactionReference": "3","accountNumber": "4","startBalance": "200.00","mutation": "+400.00","Description": "AAA","endBalance": "600.00"}] | {"result":"DUPLICATE_REFERENCE_INCORRECT_END_BALANCE","errorRecords":[{"reference":3,"accountNumber":"4"},{"reference":3,"accountNumber":"3"}]} | 200            |
        | Internal_Server_Error                              | [{"transactionReference": "3","accountNumber": "3","startBalance": "300.00","mutation": "+600.00","Description": "AAA","endBalance": "900.00"},{"transactionReference": "4","accountNumber": "4","startBalance": "200.00","mutation": "+400.00","Description": "AAA"}]                        | {"result":"INTERNAL_SERVER_ERROR","errorRecords":[]}                                                                                            | 500            |
        | Bad_Request                                        | [{"transactionReference": "3","accountNumber": "3","startBalance": "300.00","mutation": "+600.00","Description": "AAA","endBalance": "800.00"},{"transactionReference": "3","accountNumber": "4","startBalance": "200.00","mutation": "=400.00","Description": "AAA","endBalance": "600.00"}]  | {"result":"BAD_REQUEST","errorRecords":[]}                                                                                                      | 400            |

