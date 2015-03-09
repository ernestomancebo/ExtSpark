package com.flectosystems.extspark.router;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Ernesto Mancebo T on 3/9/15.
 */
public class UserRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        /*
        Add User
         */
        from("restlet:/addUser?restletMethod=POST")
                .setBody(
                        simple("INSERT INTO user(firstName, lastName) values('${header.firstName}', '${header.lastName}');" +
                                "CALL IDENTITY();")
                )
                .to("jdbc:dataSource")
                .setBody(
                        simple("SELECT * FROM user ORDER BY id DESC LIMIT 1")
                )
                .to("jdbc:dataSource");

        from("restlet:/user/{userId}?restletMethods=GET,PUT,DELETE")
                .choice()
                .when(
                        simple("${header.CamelHttpMethod} == 'GET'")
                )
                .setBody(
                        simple("SELECT * FROM user WHERE id = ${header.userId}")
                )
                .when(
                        simple("${header.CamelHttpMethod} == 'PUT'")

                )
        .setBody(
               simple("UPDATE user SET firstName = '${header.firstName}', lastName = '${header.lastName}' WHERE id = ${header.userId}")

        );
        /*
        List all user
         */
        from("restlet:/users")
                .setBody(
                        simple("SELECT * FROM user")
                )
                .to("jdbc:dataSource");

    }
}
