#

1. DB
create sequence 'user_seq';
users (id, username, hashpassword, role)
userinfo (id, lastname, firstname, phone)

2. usercheck
   (username, password)
ищем по username -> hashpassword
   bCrypt.matches(password, hashpassword);

3. useradd
   (username, password, lastname, firstname, phone)
    transaction {
        select id nextval('user_seq') as id;
        id -> users
        id -> userinfo
       }

4. registration.ftlh
    login.ftlh
    index.ftlh