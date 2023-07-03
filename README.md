## Coding Challenge

Created by Aaron Lozenkovski

This code uses the follow libraries/drivers:
    - OpenJDK 20.0.1
    - Selenium Java 4.10.0
    - ChromeDriver 114.0.5735.90 

## Improvements

Convert the search filters into enums
    This would allow testers to define all of the filter options and ensure that only valid options are chosen when writing tests for filters

Add automation-tags
    The TAFE website lacks automation tags. Having automation tags would make it a lot easier to target
    specific elements that are commonly used.

Remove any use of xPath selectors
    I tried to avoid using xpath as it's not ideal if changes are made to the DOM structure in the future.
    If I did use it I tried to keep it simple. Being able to remove any use of it completely would be ideal.