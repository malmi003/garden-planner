use gardenPlanner;

select * from user;
select * from plant;
select * from gardendiagram;
select * from plantgardenobject;
select * from lifecycle;

select * from HardinessZone;
select * from plantCompanion;
select * from plantIncompatable;

select p.* from Plant p
join plantGardenObject pgo on pgo.plantId = p.id
join GardenDiagram gd on gd.id=pgo.gardendiagramid
join user u on u.id = gd.userid
where u.id ='Y5FEYcCSarP519VTTTzqfgsRKHB3'
group by p.commonName;