-- User user/pass
INSERT INTO users (username, password, enabled)
  values ('user',
    '123',
    1);

INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');