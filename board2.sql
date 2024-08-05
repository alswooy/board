drop table board2;
CREATE TABLE `board2` (
                          `bno` int(11) NOT NULL AUTO_INCREMENT,
                          `title` varchar(45) NOT NULL,
                          `content` text NOT NULL,
                          `view_cnt` int(11) DEFAULT '0',
                          `reg_date` datetime DEFAULT CURRENT_TIMESTAMP,
                          `up_date` datetime DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
select now();

insert into board2
(title, content, reg_date)
values
    ("Test","Testcontent",now()); -- insert

select * from board2;

delete from board2 where bno = 1; -- delete

delete from board2; -- deleteAll

select bno, title, content, view_cnt, reg_date
from board2
where bno = 2; -- select

update board2
set
    title = "testUpdate",
    content = "testUpdateConent",
    up_date = now()
where bno = 2; -- update

update board2
set view_cnt = view_cnt + 1
where bno = 2; -- view_cnt

select count(*) from board2; -- count


select bno, title, content, view_cnt, reg_date
from board2
order by reg_date desc,bno desc; -- selectAll