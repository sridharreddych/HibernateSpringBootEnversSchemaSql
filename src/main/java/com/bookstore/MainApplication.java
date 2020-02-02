package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\nQuery entity history ...");
            System.out.println("------------------------");
            bookstoreService.queryEntityHistory();

            System.out.println("\nRegister new author ...");
            System.out.println("-----------------------");
            bookstoreService.registerAuthor();

            Thread.sleep(5000);

            System.out.println("\nUpdate an author ...");
            System.out.println("--------------------");
            bookstoreService.updateAuthor();

            Thread.sleep(5000);
            System.out.println("\nUpdate books of an author ...");
            System.out.println("-----------------------------");
            bookstoreService.updateBooks();

            System.out.println("\nQuery entity history ...");
            System.out.println("------------------------");
            bookstoreService.queryEntityHistory();
        };
    }
}
/*
*Hibernate Envers Auditing (schema-mysql.sql)

Description: Auditing is useful for maintaining history records. This can later help us in tracking user activities.

Key points:

for Maven, in pom.xml add the dependency hibernate-envers and JAXB API
each entity that should be audited should be annotated with @Audited
optionally, annotate entities with @AuditTable to rename the table used for auditing
rely on ValidityAuditStrategy for fast database reads, but slower writes (slower than the default DefaultAuditStrategy)
remove (disable) spring.jpa.hibernate.ddl-auto or set it to validate for avoiding schema generated from JPA annotations
create schema-mysql.sql and provide the SQL statements needed by Hibernate Envers
if the schema is not automatically found, then point it via spring.jpa.properties.org.hibernate.envers.default_catalog for MySQL or spring.jpa.properties.org.hibernate.envers.default_schema for the rest
*/