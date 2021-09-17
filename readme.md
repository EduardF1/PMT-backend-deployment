# Spring JPA H2 Setup
- the JDBC url (application.properties) must match the one used to connect to the H2 console (JDBC URL: field).
- it might be necessary to create a file in (path) C:\Users\UserName named `testdb.mv.db` (the fileName must match that 
of the database specified in the connection URL).

# Spring Security
- Solutions for lazy loading error (Hibernate transactions):
1) Add `spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true` to application.properties, however, this applies
to all entity transactions hence it may impact performance at the application level.
2) Optimal solution, add `@Proxy(lazy=false)` on the entity which requires session management.

# Deployment version url:
- https://pmt21-backend.herokuapp.com/dashboard
