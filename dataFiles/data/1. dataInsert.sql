use gardenPlanner;

INSERT INTO `companionBenefit`
(`id`,
`name`,
`description`)
VALUES
(1,'plants grow well together',''),
(2,'beneficial to garden in general',''),
(3,'combo helps bug control','');

INSERT INTO `incompatabilityType`
(`id`,
`name`,
`description`)
VALUES
(1,'plant types incompatable',''),
(2,'mismatching heights may affect access to sun',''),
(3,'conflicting water needs','');

INSERT INTO `lifecycle`
(`id`,
`name`,
`description`)
VALUES
(1,'perennial',''), 
(2,'annual',''),
(3,'biennial',''),
(4,'other','');

INSERT INTO `plantHeight`
(`id`,
`name`,
`description`)
VALUES
(1,'short',''),
(2,'medium',''),
(3,'tall',''),
(4,'very tall',''),
(5,'varies',''),
(6,'other','');

INSERT INTO `generalCategory`
(`id`,
`name`,
`description`)
VALUES
(1,'vegetable',''),
(2,'fruit',''),
(3,'herb',''),
(4,'ideal for climate',''),
(5,'pollinator',''),
(6,'wild flower',''),
(7,'flower',''),
(8,'tree',''),
(9,'shrub',''),
(10,'cactus/succulent',''),
(11,'grass',''),
(12,'fern',''),
(13,'vine',''),
(14,'native',''),
(15,'other','');

INSERT INTO `plantSunRequirement`
(`id`,
`name`,
`description`)
VALUES
(1,'full',''),
(2,'partial',''),
(3,'very little',''),
(4,'other','');

INSERT INTO `waterPreference`
(`id`,
`name`,
`description`)
VALUES
(1,'in water',''),
(2,'wet',''),
(3,'wet mesic',''),
(4,'mesic',''),
(5,'dry mesic',''),
(6,'dry',''),
(7,'other','');

-- INSERT INTO `hardinessZone` (`zipcode`, `zone`,`lastSpringFrost`, `firstFallFrost`) VALUES (55422,'4b', '2000-05-04','2000-10-02');


