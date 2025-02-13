# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?

    Comma-separated values (CSV) is a text file format that uses commas to separate values, and newlines to separate records.


2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

   Declaring List<IEmployee> instead of ArrayList<HourlyEmployee> allows for polymorphism, enabling the storage of different IEmployee types, such as HourlyEmployee and SalaryEmployee. It promotes interface-based programming, making the code more flexible and easily extendable.


3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

   It's a has-a relationship.


4. Can you provide an example of a has-a relationship in your code (if one exists)?

   PayStub has-a IEmployee


5. Can you provide an example of an is-a relationship in your code (if one exists)?

   HourlyEmployee is-a AbstractEmployee


6. What is the difference between an interface and an abstract class?

    * First of all, abstract class is used to represent common states and behaviors that subclasses can share or override. It allows you to define both abstract methods (without implementation) and concrete methods (with implementation). But interface is used to define a contract specifying public methods that implementing classes must provide.
    * Regarding inheritance, a class can extend only one abstract class, as Java supports single inheritance for classes. But a class can implement multiple interfaces, allowing for multiple inheritance of behavior.
    * About instance variables, abstract class can have instance variables (fields) that store state. But interface cannot have instance variables, only constants (static final variables).
    * About method modifiers, abstract class can have a wider range of access modifiers (private, public, protected) for its methods. But all methods of interface are public by default.
    * Regarding constructors, abstract class can have constructors that can be invoked by subclasses using the super keyword. But interface cannot have constructors. Interfaces cannot be instantiated directly.


7. What is the advantage of using an interface over an abstract class?

   An interface allows a class to implement multiple behaviors from different sources, providing more flexibility, while an abstract class limits inheritance to one class and enforces shared implementation details. Adding methods to an interface does not require changes in implementing classes (if methods are default or static), unlike in abstract classes.


8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

   The code is not valid because Java generics do not support primitive types like int. Java's generics require reference types.


9. Which class/method is described as the "driver" for your application? 

   The PayrollGenerator class serves as the driver for the application. Its main() method is the entry point, and it uses various utility classes and interfaces to generate payroll.


10. How do you create a temporary folder for JUnit Testing? 

    We can use @TempDir annotation to create temporary folders for testing. In TestPayrollGenerator, @TempDir assigns a temporary directory to tempDir, allowing safe file operations without modifying actual files.

## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

* According to Pew Research Center's article "Gender pay gap in U.S. hasn't changed much in two decades" (2023), despite two decades of discussion and initiatives around gender pay equity, the pay gap has remained consistent. Women earned an average of 82% of what men earned in 2022, barely changed from 80% in 2002. This persistent gap in hourly earnings spans both full-time and part-time workers.
* Further insights from the European Parliament's article "Understanding the gender pay gap: definition and causes" (2023) reveal several key factors contributing to pay inequity. About 24% of the total gender pay gap can be attributed to women's overrepresentation in relatively low-paying sectors. Even in management positions, women made up only 34% of managers in the EU in 2020 and earned 23% less per hour than male managers. The article also mentions how career interruptions for family responsibilities significantly affect women, with 33% of employed women in the EU having work interruptions for childcare compared to just 1.3% of men.
* About system change, we may need to include gender, age, department, job level, job title, education level, years of experience, etc. Because all these data help identify when pay differences exist between employees with similar qualifications and experience.
* We should analyze the data at multiple points in the payroll process: 1) Before pretax deductions, as this represents the initial salary and helps identify core pay disparities. The European Parliament's research shows women earn 23% less per hour than men in management positions, so examining base pay is crucial. 2) After standard deductions but before pretax benefits, because according to European Parliament's research, career break and family responsibilities impact women significantly, which may lead to different benefit choices.
