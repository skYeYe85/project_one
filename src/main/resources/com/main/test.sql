
INSERT INTO EXOPLANETS VALUES (NULL, --ID
                               'Gliese 876 d', --Planetname
                               0.017, --Masse
							   —, --Radius
							   1.94, --Periode
							   0.02080665, --AstroEinheit
							   0.081, --Exzentrizitaet
							   50, --Bahnneigung
							   0.004427, --Winkelabstand
							   2005, --Entdeckung
							   '2014-04-01')
						 
INSERT INTO EXOPLANETS VALUES (null, 'a',0,0,0,0,0,0,0,0,'2014-04-01')


CREATE TABLE TEST(ID INTEGER, DATUM DATE);
SELECT * FROM TEST;
INSERT INTO TEST VALUES(NULL, NULL);
INSERT INTO TEST VALUES(NULL, '2014-1');