# carta-healthcare-TestSolution
This repo is for the solution of problem statement for carta-healthcare problem: https://github.com/carta-healthcare/carta-work-tests/tree/master/java

# Considerations
Following corner cases have been considered apart from happy scenarios while solving the problem given at  -  https://github.com/carta-healthcare/carta-work-tests/tree/master/java

* If incorrect/ unreadable file is given as input then API return response as BAD-REQUEST with error message.
* If the given column name is not found, API returns response as BAD-REQUEST with error message - "Given column name not found".
* If the given column names does not contain valid numbers, API returns response as BAD_REQUEST with error message as- NumberFormat Exception.
* If the file only contains header with no data, API returns output as 0.0 .