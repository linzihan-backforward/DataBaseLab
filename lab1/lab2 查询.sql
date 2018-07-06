#批处理操作
create table YOUNG_ACTOR select * from actor
	where BYEAR  >1989

#创建视图
CREATE VIEW mainactor as SELECT actin.ACTID,actor.ANAME,actor.BYEAR,COUNT(actin.ISLEADING),MAX(film.GRADE)
 FROM actin,actor,film
 WHERE actor.ACTID=actin.ACTID AND film.FID=actin.FID AND actor.BYEAR>1979 AND actor.BYEAR<1990  AND actin.ISLEADING='Y';
 		

#触发器	
CREATE
    TRIGGER tri1 BEFORE INSERT
    ON film
    FOR EACH ROW BEGIN
	IF(new.DNAME = '周星驰') THEN
	SET new.FTYPE = '喜剧片';
	END IF;
    END; 


				
#查询1 查询“战狼”这部电影在洪山区各家影院的2017年的上映情况，并按照上映的月份的降序排列；
SELECT showmovie.PRICE,showmovie.MONTHS,theater.TNAME,theater.ADDRESS
 FROM showmovie,theater,film
 WHERE showmovie.YEARR=2017 AND showmovie.FID=film.FID AND film.FNAME='战狼' AND showmovie.TID=theater.TID AND theater.TAREA='洪山区'
 ORDER BY showmovie.MONTHS DESC;

#查询2 查询所有无参演演员信息的电影的基本信息，并且将结果按照电影类型的升序排列，相同类型的电影则按照用户评分的降序排列； 
SELECT film.FID,film.FNAME,film.FTYPE,film.DNAME,film.LEN,film.IS3D,film.GRADE
	FROM film
	WHERE film.FID NOT IN(
   SELECT film.FID
  	FROM film,actin
  	WHERE film.FID=actin.FID)
 ORDER BY film.FTYPE ASC,film.GRADE DESC

#查询3 查询所有直到2017年仍未上映的电影编号、电影名称、导演姓名；
SELECT film.FID,film.FNAME,film.DNAME
 FROM film
 WHERE film.FID NOT IN(
 	SELECT film.FID
	FROM film,showmovie
	WHERE film.FID=showmovie.FID AND showmovie.YEARR=2017)

#查询4 查询在各家电影院均上映过的电影编号；
SELECT temp.FID
 FROM (
  SELECT showmovie.FID ,COUNT(showmovie.TID) as b
	FROM showmovie 
	GROUP BY showmovie.FID
 ) as temp
 WHERE b=(SELECT COUNT(*) FROM theater)

#查询5 查询所有用户评分低于80分或者高于89分的电影编号、电影名称、导演姓名及其用户评分，要求where子句中只能有一个条件表达式；
SELECT film.FID,film.FNAME,film.DNAME,film.GRADE
FROM film
WHERE film.GRADE NOT BETWEEN 80 AND 89;

#查询6 查询每个导演所执导的全部影片的最低和最高用户评分； 
SELECT MIN(film.GRADE),MAX(film.GRADE)
FROM film
GROUP BY film.DNAME
#查询7 查询至少执导过2部电影的导演姓名、执导电影数量；
SELECT temp.DNAME,temp.Numberr
FROM (SELECT film.DNAME,COUNT(film.DNAME) as Numberr
FROM film
GROUP BY film.DNAME) as temp
WHERE temp.Numberr>=2
#查询8 查询至少2部电影的用户评分超过80分的导演及其执导过的影片数量、平均用户评分；
SELECT temp.DNAME,temp.Numberr,temp.graave
FROM (SELECT film.DNAME,COUNT(film.DNAME) as Numberr,AVG(film.GRADE) as graave
FROM film
WHERE film.GRADE>80
GROUP BY film.DNAME) as temp
WHERE temp.Numberr>=2


#查询9 查询至少执导过2部电影的导演姓名以及跟这些导演合作过的演员编号、姓名；
SELECT dir.DNAME,actor.ACTID,actor.ANAME
FROM (SELECT temp.DNAME,temp.Numberr
FROM (SELECT film.DNAME,COUNT(film.DNAME) as Numberr
FROM film
GROUP BY film.DNAME) as temp
WHERE temp.Numberr>=2) as dir,actor,actin,film
WHERE actor.ACTID=actin.ACTID AND film.FID=actin.FID AND film.DNAME=dir.DNAME
#查询10 查询每个演员担任主角的电影中的平均用户评分； 
SELECT actin.ACTID,AVG(film.GRADE)
FROM actin,film
WHERE actin.FID=film.FID AND actin.ISLEADING='Y' 
GROUP BY actin.ACTID
#查询11 查询用户评分超过90分的电影的最早上映年月； 
SELECT showmovie.YEARR,showmovie.MONTHS
FROM showmovie,film
WHERE showmovie.FID=film.FID AND film.GRADE>90
ORDER BY showmovie.YEARR ASC,showmovie.MONTHS ASC LIMIT 1
#查询12 查询用户评分超过90分的电影的最早上映年月及其相应的上映影院编号； 
SELECT showmovie.YEARR,showmovie.MONTHS,showmovie.TID
FROM showmovie,film
WHERE showmovie.FID=film.FID AND film.GRADE>90
ORDER BY showmovie.YEARR ASC,showmovie.MONTHS ASC LIMIT 1

#查询13 查询每个电影的上映总次数；
SELECT temp.FID, temp.numberr
 FROM (
  SELECT showmovie.FID ,COUNT(showmovie.TID) as numberr
	FROM showmovie 
	GROUP BY showmovie.FID
 ) as temp
#查询14 查询执导过动作片，或者警匪片，或者枪战片的导演的姓名，要求where子句中只能有一个条件表达式；
SELECT DISTINCT film.DNAME
FROM film
WHERE film.FTYPE IN('动作片','警匪片','枪战片');

#查询15 查询所有“战狼”系列的电影的编号、电影名称、上映电影院名称及其上映年月，结果按照电影名称的升序排列；
SELECT film.FID,film.FNAME,theater.TNAME,showmovie.YEARR,showmovie.MONTHS
FROM film,theater,showmovie
WHERE film.FID=showmovie.FID AND theater.TID=showmovie.TID AND LOCATE('战狼',film.FNAME) <>0
#查询16 查询在同一个年月上映1号和2号电影的影院编号；
SELECT mon1.TID
FROM (SELECT * FROM showmovie
WHERE showmovie.FID=1 ) as mon1,(SELECT * FROM showmovie WHERE showmovie.FID=2) as mon2
WHERE mon1.TID=mon2.TID AND mon1.YEARR=mon2.YEARR AND mon1.MONTHS=mon2.MONTHS


#查询17 查询所有没参演过用户评分85分以下电影的演员的编号、姓名；
SELECT actor.ACTID,actor.ANAME
FROM actor
WHERE actor.ACTID NOT IN(
 SELECT actin.ACTID
 FROM actin,film
 WHERE actin.FID=film.FID AND film.GRADE <85
)

#查询18 查询参演过“吴宇森”执导过的所有电影的演员姓名；
SELECT actor.ANAME
FROM actor,film,actin
WHERE actin.FID=film.FID AND film.DNAME='吴宇森' AND actin.ACTID=actor.ACTID
#查询19 查询所有的演员的编号、姓名及其参演过的电影名称，要求即使该演员未参演过任何电影也要能够输出其编号、姓名；
SELECT actor.ACTID,actor.ANAME,film.FNAME
FROM actor LEFT JOIN actin
ON actor.ACTID=actin.ACTID LEFT JOIN film
ON actin.FID=film.FID


#查询20 查询所有上映超过3次但没有用户评分的电影编号、名称。
SELECT temp.FID,film.FNAME
FROM(SELECT showmovie.FID,COUNT(showmovie.FID) as cnt
FROM showmovie
GROUP BY showmovie.FID) as temp,film
WHERE temp.FID=film.FID AND temp.cnt>3 AND film.GRADE IS NULL