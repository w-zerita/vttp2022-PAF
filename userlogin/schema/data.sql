-- delete data if exists
truncate table user;

insert into user(username, password)
    values 
        ('fred', sha1('fred@123')),
        ('wilma', sha1('wilma@123')),
        ('betty', sha1('betty@123')),
        ('barney', sha1('barney@123')),
        ('dino', sha1('dino@123'));