-- Boards
insert into boards(name, slug, description) values ('Dyskusja ogólna', 'b', 'test')
insert into boards(name, slug, description) values ('Java', 'j', 'test')
insert into boards(name, slug, description) values ('Polandball', 'pb', 'test')
insert into boards(name, slug, description) values ('Polityka', 'p', 'test')
insert into boards(name, slug, description) values ('Illuminati', 'x', 'test')
insert into boards(name, slug, description) values ('Lolcatz', 'lolz', 'test')

insert into threads(board_id, title) values ('1', 'test')

insert into messages(thread_id, body, created_at) values('1', 'test', CURRENT_TIMESTAMP)
insert into messages(thread_id, body, created_at) values('1', 'test', CURRENT_TIMESTAMP)
insert into messages(thread_id, body, created_at) values('1', 'test', CURRENT_TIMESTAMP)
insert into messages(thread_id, body, created_at) values('1', 'test', CURRENT_TIMESTAMP)
