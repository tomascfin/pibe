/*
Navicat PGSQL Data Transfer

Source Server         : Localhost
Source Server Version : 90507
Source Host           : localhost:5432
Source Database       : pibe
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90507
File Encoding         : 65001

Date: 2017-09-17 12:20:57
*/


-- ----------------------------
-- Sequence structure for areas_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."areas_id_seq";
CREATE SEQUENCE "public"."areas_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."areas_id_seq"', 4, true);

-- ----------------------------
-- Sequence structure for reclamo_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."reclamo_id_seq";
CREATE SEQUENCE "public"."reclamo_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 7
 CACHE 1;
SELECT setval('"public"."reclamo_id_seq"', 7, true);

-- ----------------------------
-- Sequence structure for roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."roles_id_seq";
CREATE SEQUENCE "public"."roles_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."roles_id_seq"', 1, true);

-- ----------------------------
-- Sequence structure for usuarios_id_usuario_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."usuarios_id_usuario_seq";
CREATE SEQUENCE "public"."usuarios_id_usuario_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for agendamiento
-- ----------------------------
DROP TABLE IF EXISTS "public"."agendamiento";
CREATE TABLE "public"."agendamiento" (
"id" int8 NOT NULL,
"detalle_agendamiento" varchar(1024) COLLATE "default",
"email_contacto" varchar(128) COLLATE "default",
"fecha_inicio" timestamp(6),
"fecha_termino" timestamp(6),
"nombre_contacto" varchar(128) COLLATE "default",
"prioridad" int4 NOT NULL,
"telefono_contacto" varchar(128) COLLATE "default",
"id_entidad" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of agendamiento
-- ----------------------------

-- ----------------------------
-- Table structure for areas
-- ----------------------------
DROP TABLE IF EXISTS "public"."areas";
CREATE TABLE "public"."areas" (
"id" int4 DEFAULT nextval('areas_id_seq'::regclass) NOT NULL,
"correo_area" varchar(32) COLLATE "default" NOT NULL,
"nombre_area" varchar(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of areas
-- ----------------------------
INSERT INTO "public"."areas" VALUES ('1', 'tomas.cfin@gmail.com', 'Finanzas');
INSERT INTO "public"."areas" VALUES ('2', 'tomas.i.rm25@gmail.com', 'Area tecnica');
INSERT INTO "public"."areas" VALUES ('3', 'anflonatom@gmail.com', 'Comercial');
INSERT INTO "public"."areas" VALUES ('4', 'zeosch@gmail.com', 'RRHH');

-- ----------------------------
-- Table structure for comuna
-- ----------------------------
DROP TABLE IF EXISTS "public"."comuna";
CREATE TABLE "public"."comuna" (
"comuna_id" int4 NOT NULL,
"comuna_nombre" varchar(20) COLLATE "default" NOT NULL,
"provincia_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of comuna
-- ----------------------------
INSERT INTO "public"."comuna" VALUES ('1', 'Iquique', '3');
INSERT INTO "public"."comuna" VALUES ('2', 'Alto Hospicio', '3');
INSERT INTO "public"."comuna" VALUES ('3', 'Pozo Almonte', '4');
INSERT INTO "public"."comuna" VALUES ('4', 'Camiña', '4');
INSERT INTO "public"."comuna" VALUES ('5', 'Colchane', '4');
INSERT INTO "public"."comuna" VALUES ('6', 'Huara', '4');
INSERT INTO "public"."comuna" VALUES ('7', 'Pica', '4');
INSERT INTO "public"."comuna" VALUES ('8', 'Antofagasta', '5');
INSERT INTO "public"."comuna" VALUES ('9', 'Mejillones', '5');
INSERT INTO "public"."comuna" VALUES ('10', 'Sierra Gorda', '5');
INSERT INTO "public"."comuna" VALUES ('11', 'Taltal', '5');
INSERT INTO "public"."comuna" VALUES ('12', 'Calama', '6');
INSERT INTO "public"."comuna" VALUES ('13', 'Ollagüe', '6');
INSERT INTO "public"."comuna" VALUES ('14', 'San Pedro de Atacama', '6');
INSERT INTO "public"."comuna" VALUES ('15', 'Tocopilla', '7');
INSERT INTO "public"."comuna" VALUES ('16', 'María Elena', '7');
INSERT INTO "public"."comuna" VALUES ('17', 'Copiapó', '8');
INSERT INTO "public"."comuna" VALUES ('18', 'Caldera', '8');
INSERT INTO "public"."comuna" VALUES ('19', 'Tierra Amarilla', '8');
INSERT INTO "public"."comuna" VALUES ('20', 'Chañaral', '9');
INSERT INTO "public"."comuna" VALUES ('21', 'Diego de Almagro', '9');
INSERT INTO "public"."comuna" VALUES ('22', 'Vallenar', '10');
INSERT INTO "public"."comuna" VALUES ('23', 'Alto del Carmen', '10');
INSERT INTO "public"."comuna" VALUES ('24', 'Freirina', '10');
INSERT INTO "public"."comuna" VALUES ('25', 'Huasco', '10');
INSERT INTO "public"."comuna" VALUES ('26', 'La Serena', '11');
INSERT INTO "public"."comuna" VALUES ('27', 'Coquimbo', '11');
INSERT INTO "public"."comuna" VALUES ('28', 'Andacollo', '11');
INSERT INTO "public"."comuna" VALUES ('29', 'La Higuera', '11');
INSERT INTO "public"."comuna" VALUES ('30', 'Paihuano', '11');
INSERT INTO "public"."comuna" VALUES ('31', 'Vicuña', '11');
INSERT INTO "public"."comuna" VALUES ('32', 'Illapel', '12');
INSERT INTO "public"."comuna" VALUES ('33', 'Canela', '12');
INSERT INTO "public"."comuna" VALUES ('34', 'Los Vilos', '12');
INSERT INTO "public"."comuna" VALUES ('35', 'Salamanca', '12');
INSERT INTO "public"."comuna" VALUES ('36', 'Ovalle', '13');
INSERT INTO "public"."comuna" VALUES ('37', 'Combarbalá', '13');
INSERT INTO "public"."comuna" VALUES ('38', 'Monte Patria', '13');
INSERT INTO "public"."comuna" VALUES ('39', 'Punitaqui', '13');
INSERT INTO "public"."comuna" VALUES ('40', 'Río Hurtado', '13');
INSERT INTO "public"."comuna" VALUES ('41', 'Valparaíso', '14');
INSERT INTO "public"."comuna" VALUES ('42', 'Casablanca', '14');
INSERT INTO "public"."comuna" VALUES ('43', 'Concón', '14');
INSERT INTO "public"."comuna" VALUES ('44', 'Juan Fernández', '14');
INSERT INTO "public"."comuna" VALUES ('45', 'Puchuncaví', '14');
INSERT INTO "public"."comuna" VALUES ('46', 'Quintero', '14');
INSERT INTO "public"."comuna" VALUES ('47', 'Viña del Mar', '14');
INSERT INTO "public"."comuna" VALUES ('48', 'Isla de Pascua', '15');
INSERT INTO "public"."comuna" VALUES ('49', 'Los Andes', '16');
INSERT INTO "public"."comuna" VALUES ('50', 'Calle Larga', '16');
INSERT INTO "public"."comuna" VALUES ('51', 'Rinconada', '16');
INSERT INTO "public"."comuna" VALUES ('52', 'San Esteban', '16');
INSERT INTO "public"."comuna" VALUES ('53', 'La Ligua', '17');
INSERT INTO "public"."comuna" VALUES ('54', 'Cabildo', '17');
INSERT INTO "public"."comuna" VALUES ('55', 'Papudo', '17');
INSERT INTO "public"."comuna" VALUES ('56', 'Petorca', '17');
INSERT INTO "public"."comuna" VALUES ('57', 'Zapallar', '17');
INSERT INTO "public"."comuna" VALUES ('58', 'Quillota', '18');
INSERT INTO "public"."comuna" VALUES ('59', 'La Calera', '18');
INSERT INTO "public"."comuna" VALUES ('60', 'Hijuelas', '18');
INSERT INTO "public"."comuna" VALUES ('61', 'La Cruz', '18');
INSERT INTO "public"."comuna" VALUES ('62', 'Nogales', '18');
INSERT INTO "public"."comuna" VALUES ('63', 'San Antonio', '19');
INSERT INTO "public"."comuna" VALUES ('64', 'Algarrobo', '19');
INSERT INTO "public"."comuna" VALUES ('65', 'Cartagena', '19');
INSERT INTO "public"."comuna" VALUES ('66', 'El Quisco', '19');
INSERT INTO "public"."comuna" VALUES ('67', 'El Tabo', '19');
INSERT INTO "public"."comuna" VALUES ('68', 'Santo Domingo', '19');
INSERT INTO "public"."comuna" VALUES ('69', 'San Felipe', '20');
INSERT INTO "public"."comuna" VALUES ('70', 'Catemu', '20');
INSERT INTO "public"."comuna" VALUES ('71', 'Llay Llay', '20');
INSERT INTO "public"."comuna" VALUES ('72', 'Panquehue', '20');
INSERT INTO "public"."comuna" VALUES ('73', 'Putaendo', '20');
INSERT INTO "public"."comuna" VALUES ('74', 'Santa María', '20');
INSERT INTO "public"."comuna" VALUES ('75', 'Quilpué', '21');
INSERT INTO "public"."comuna" VALUES ('76', 'Limache', '21');
INSERT INTO "public"."comuna" VALUES ('77', 'Olmué', '21');
INSERT INTO "public"."comuna" VALUES ('78', 'Villa Alemana', '21');
INSERT INTO "public"."comuna" VALUES ('79', 'Rancagua', '22');
INSERT INTO "public"."comuna" VALUES ('80', 'Codegua', '22');
INSERT INTO "public"."comuna" VALUES ('81', 'Coinco', '22');
INSERT INTO "public"."comuna" VALUES ('82', 'Coltauco', '22');
INSERT INTO "public"."comuna" VALUES ('83', 'Doñihue', '22');
INSERT INTO "public"."comuna" VALUES ('84', 'Graneros', '22');
INSERT INTO "public"."comuna" VALUES ('85', 'Las Cabras', '22');
INSERT INTO "public"."comuna" VALUES ('86', 'Machalí', '22');
INSERT INTO "public"."comuna" VALUES ('87', 'Malloa', '22');
INSERT INTO "public"."comuna" VALUES ('88', 'Mostazal', '22');
INSERT INTO "public"."comuna" VALUES ('89', 'Olivar', '22');
INSERT INTO "public"."comuna" VALUES ('90', 'Peumo', '22');
INSERT INTO "public"."comuna" VALUES ('91', 'Pichidegua', '22');
INSERT INTO "public"."comuna" VALUES ('92', 'Quinta de Tilcoco', '22');
INSERT INTO "public"."comuna" VALUES ('93', 'Rengo', '22');
INSERT INTO "public"."comuna" VALUES ('94', 'Requínoa', '22');
INSERT INTO "public"."comuna" VALUES ('95', 'San Vicente', '22');
INSERT INTO "public"."comuna" VALUES ('96', 'Pichilemu', '23');
INSERT INTO "public"."comuna" VALUES ('97', 'La Estrella', '23');
INSERT INTO "public"."comuna" VALUES ('98', 'Litueche', '23');
INSERT INTO "public"."comuna" VALUES ('99', 'Marchihue', '23');
INSERT INTO "public"."comuna" VALUES ('100', 'Navidad', '23');
INSERT INTO "public"."comuna" VALUES ('101', 'Paredones', '23');
INSERT INTO "public"."comuna" VALUES ('102', 'San Fernando', '24');
INSERT INTO "public"."comuna" VALUES ('103', 'Chépica', '24');
INSERT INTO "public"."comuna" VALUES ('104', 'Chimbarongo', '24');
INSERT INTO "public"."comuna" VALUES ('105', 'Lolol', '24');
INSERT INTO "public"."comuna" VALUES ('106', 'Nancagua', '24');
INSERT INTO "public"."comuna" VALUES ('107', 'Palmilla', '24');
INSERT INTO "public"."comuna" VALUES ('108', 'Peralillo', '24');
INSERT INTO "public"."comuna" VALUES ('109', 'Placilla', '24');
INSERT INTO "public"."comuna" VALUES ('110', 'Pumanque', '24');
INSERT INTO "public"."comuna" VALUES ('111', 'Santa Cruz', '24');
INSERT INTO "public"."comuna" VALUES ('112', 'Talca', '25');
INSERT INTO "public"."comuna" VALUES ('113', 'Constitución', '25');
INSERT INTO "public"."comuna" VALUES ('114', 'Curepto', '25');
INSERT INTO "public"."comuna" VALUES ('115', 'Empedrado', '25');
INSERT INTO "public"."comuna" VALUES ('116', 'Maule', '25');
INSERT INTO "public"."comuna" VALUES ('117', 'Pelarco', '25');
INSERT INTO "public"."comuna" VALUES ('118', 'Pencahue', '25');
INSERT INTO "public"."comuna" VALUES ('119', 'Río Claro', '25');
INSERT INTO "public"."comuna" VALUES ('120', 'San Clemente', '25');
INSERT INTO "public"."comuna" VALUES ('121', 'San Rafael', '25');
INSERT INTO "public"."comuna" VALUES ('122', 'Cauquenes', '26');
INSERT INTO "public"."comuna" VALUES ('123', 'Chanco', '26');
INSERT INTO "public"."comuna" VALUES ('124', 'Pelluhue', '26');
INSERT INTO "public"."comuna" VALUES ('125', 'Curicó', '27');
INSERT INTO "public"."comuna" VALUES ('126', 'Hualañé', '27');
INSERT INTO "public"."comuna" VALUES ('127', 'Licantén', '27');
INSERT INTO "public"."comuna" VALUES ('128', 'Molina', '27');
INSERT INTO "public"."comuna" VALUES ('129', 'Rauco', '27');
INSERT INTO "public"."comuna" VALUES ('130', 'Romeral', '27');
INSERT INTO "public"."comuna" VALUES ('131', 'Sagrada Familia', '27');
INSERT INTO "public"."comuna" VALUES ('132', 'Teno', '27');
INSERT INTO "public"."comuna" VALUES ('133', 'Vichuquén', '27');
INSERT INTO "public"."comuna" VALUES ('134', 'Linares', '28');
INSERT INTO "public"."comuna" VALUES ('135', 'Colbún', '28');
INSERT INTO "public"."comuna" VALUES ('136', 'Longaví', '28');
INSERT INTO "public"."comuna" VALUES ('137', 'Parral', '28');
INSERT INTO "public"."comuna" VALUES ('138', 'Retiro', '28');
INSERT INTO "public"."comuna" VALUES ('139', 'San Javier', '28');
INSERT INTO "public"."comuna" VALUES ('140', 'Villa Alegre', '28');
INSERT INTO "public"."comuna" VALUES ('141', 'Yerbas Buenas', '28');
INSERT INTO "public"."comuna" VALUES ('142', 'Concepción', '29');
INSERT INTO "public"."comuna" VALUES ('143', 'Coronel', '29');
INSERT INTO "public"."comuna" VALUES ('144', 'Chiguayante', '29');
INSERT INTO "public"."comuna" VALUES ('145', 'Florida', '29');
INSERT INTO "public"."comuna" VALUES ('146', 'Hualqui', '29');
INSERT INTO "public"."comuna" VALUES ('147', 'Lota', '29');
INSERT INTO "public"."comuna" VALUES ('148', 'Penco', '29');
INSERT INTO "public"."comuna" VALUES ('149', 'San Pedro de la Paz', '29');
INSERT INTO "public"."comuna" VALUES ('150', 'Santa Juana', '29');
INSERT INTO "public"."comuna" VALUES ('151', 'Talcahuano', '29');
INSERT INTO "public"."comuna" VALUES ('152', 'Tomé', '29');
INSERT INTO "public"."comuna" VALUES ('153', 'Hualpén', '29');
INSERT INTO "public"."comuna" VALUES ('154', 'Lebu', '30');
INSERT INTO "public"."comuna" VALUES ('155', 'Arauco', '30');
INSERT INTO "public"."comuna" VALUES ('156', 'Cañete', '30');
INSERT INTO "public"."comuna" VALUES ('157', 'Contulmo', '30');
INSERT INTO "public"."comuna" VALUES ('158', 'Curanilahue', '30');
INSERT INTO "public"."comuna" VALUES ('159', 'Los Álamos', '30');
INSERT INTO "public"."comuna" VALUES ('160', 'Tirúa', '30');
INSERT INTO "public"."comuna" VALUES ('161', 'Los Ángeles', '31');
INSERT INTO "public"."comuna" VALUES ('162', 'Antuco', '31');
INSERT INTO "public"."comuna" VALUES ('163', 'Cabrero', '31');
INSERT INTO "public"."comuna" VALUES ('164', 'Laja', '31');
INSERT INTO "public"."comuna" VALUES ('165', 'Mulchén', '31');
INSERT INTO "public"."comuna" VALUES ('166', 'Nacimiento', '31');
INSERT INTO "public"."comuna" VALUES ('167', 'Negrete', '31');
INSERT INTO "public"."comuna" VALUES ('168', 'Quilaco', '31');
INSERT INTO "public"."comuna" VALUES ('169', 'Quilleco', '31');
INSERT INTO "public"."comuna" VALUES ('170', 'San Rosendo', '31');
INSERT INTO "public"."comuna" VALUES ('171', 'Santa Bárbara', '31');
INSERT INTO "public"."comuna" VALUES ('172', 'Tucapel', '31');
INSERT INTO "public"."comuna" VALUES ('173', 'Yumbel', '31');
INSERT INTO "public"."comuna" VALUES ('174', 'Alto Biobío', '31');
INSERT INTO "public"."comuna" VALUES ('175', 'Chillán', '32');
INSERT INTO "public"."comuna" VALUES ('176', 'Bulnes', '32');
INSERT INTO "public"."comuna" VALUES ('177', 'Cobquecura', '32');
INSERT INTO "public"."comuna" VALUES ('178', 'Coelemu', '32');
INSERT INTO "public"."comuna" VALUES ('179', 'Coihueco', '32');
INSERT INTO "public"."comuna" VALUES ('180', 'Chillán Viejo', '32');
INSERT INTO "public"."comuna" VALUES ('181', 'El Carmen', '32');
INSERT INTO "public"."comuna" VALUES ('182', 'Ninhue', '32');
INSERT INTO "public"."comuna" VALUES ('183', 'Ñiquén', '32');
INSERT INTO "public"."comuna" VALUES ('184', 'Pemuco', '32');
INSERT INTO "public"."comuna" VALUES ('185', 'Pinto', '32');
INSERT INTO "public"."comuna" VALUES ('186', 'Portezuelo', '32');
INSERT INTO "public"."comuna" VALUES ('187', 'Quillón', '32');
INSERT INTO "public"."comuna" VALUES ('188', 'Quirihue', '32');
INSERT INTO "public"."comuna" VALUES ('189', 'Ránquil', '32');
INSERT INTO "public"."comuna" VALUES ('190', 'San Carlos', '32');
INSERT INTO "public"."comuna" VALUES ('191', 'San Fabián', '32');
INSERT INTO "public"."comuna" VALUES ('192', 'San Ignacio', '32');
INSERT INTO "public"."comuna" VALUES ('193', 'San Nicolás', '32');
INSERT INTO "public"."comuna" VALUES ('194', 'Treguaco', '32');
INSERT INTO "public"."comuna" VALUES ('195', 'Yungay', '32');
INSERT INTO "public"."comuna" VALUES ('196', 'Temuco', '33');
INSERT INTO "public"."comuna" VALUES ('197', 'Carahue', '33');
INSERT INTO "public"."comuna" VALUES ('198', 'Cunco', '33');
INSERT INTO "public"."comuna" VALUES ('199', 'Curarrehue', '33');
INSERT INTO "public"."comuna" VALUES ('200', 'Freire', '33');
INSERT INTO "public"."comuna" VALUES ('201', 'Galvarino', '33');
INSERT INTO "public"."comuna" VALUES ('202', 'Gorbea', '33');
INSERT INTO "public"."comuna" VALUES ('203', 'Lautaro', '33');
INSERT INTO "public"."comuna" VALUES ('204', 'Loncoche', '33');
INSERT INTO "public"."comuna" VALUES ('205', 'Melipeuco', '33');
INSERT INTO "public"."comuna" VALUES ('206', 'Nueva Imperial', '33');
INSERT INTO "public"."comuna" VALUES ('207', 'Padre las Casas', '33');
INSERT INTO "public"."comuna" VALUES ('208', 'Perquenco', '33');
INSERT INTO "public"."comuna" VALUES ('209', 'Pitrufquén', '33');
INSERT INTO "public"."comuna" VALUES ('210', 'Pucón', '33');
INSERT INTO "public"."comuna" VALUES ('211', 'Saavedra', '33');
INSERT INTO "public"."comuna" VALUES ('212', 'Teodoro Schmidt', '33');
INSERT INTO "public"."comuna" VALUES ('213', 'Toltén', '33');
INSERT INTO "public"."comuna" VALUES ('214', 'Vilcún', '33');
INSERT INTO "public"."comuna" VALUES ('215', 'Villarrica', '33');
INSERT INTO "public"."comuna" VALUES ('216', 'Cholchol', '33');
INSERT INTO "public"."comuna" VALUES ('217', 'Angol', '34');
INSERT INTO "public"."comuna" VALUES ('218', 'Collipulli', '34');
INSERT INTO "public"."comuna" VALUES ('219', 'Curacautín', '34');
INSERT INTO "public"."comuna" VALUES ('220', 'Ercilla', '34');
INSERT INTO "public"."comuna" VALUES ('221', 'Lonquimay', '34');
INSERT INTO "public"."comuna" VALUES ('222', 'Los Sauces', '34');
INSERT INTO "public"."comuna" VALUES ('223', 'Lumaco', '34');
INSERT INTO "public"."comuna" VALUES ('224', 'Purén', '34');
INSERT INTO "public"."comuna" VALUES ('225', 'Renaico', '34');
INSERT INTO "public"."comuna" VALUES ('226', 'Traiguén', '34');
INSERT INTO "public"."comuna" VALUES ('227', 'Victoria', '34');
INSERT INTO "public"."comuna" VALUES ('228', 'Puerto Montt', '37');
INSERT INTO "public"."comuna" VALUES ('229', 'Calbuco', '37');
INSERT INTO "public"."comuna" VALUES ('230', 'Cochamó', '37');
INSERT INTO "public"."comuna" VALUES ('231', 'Fresia', '37');
INSERT INTO "public"."comuna" VALUES ('232', 'Frutillar', '37');
INSERT INTO "public"."comuna" VALUES ('233', 'Los Muermos', '37');
INSERT INTO "public"."comuna" VALUES ('234', 'Llanquihue', '37');
INSERT INTO "public"."comuna" VALUES ('235', 'Maullín', '37');
INSERT INTO "public"."comuna" VALUES ('236', 'Puerto Varas', '37');
INSERT INTO "public"."comuna" VALUES ('237', 'Castro', '38');
INSERT INTO "public"."comuna" VALUES ('238', 'Ancud', '38');
INSERT INTO "public"."comuna" VALUES ('239', 'Chonchi', '38');
INSERT INTO "public"."comuna" VALUES ('240', 'Curaco de Vélez', '38');
INSERT INTO "public"."comuna" VALUES ('241', 'Dalcahue', '38');
INSERT INTO "public"."comuna" VALUES ('242', 'Puqueldón', '38');
INSERT INTO "public"."comuna" VALUES ('243', 'Queilén', '38');
INSERT INTO "public"."comuna" VALUES ('244', 'Quellón', '38');
INSERT INTO "public"."comuna" VALUES ('245', 'Quemchi', '38');
INSERT INTO "public"."comuna" VALUES ('246', 'Quinchao', '38');
INSERT INTO "public"."comuna" VALUES ('247', 'Osorno', '39');
INSERT INTO "public"."comuna" VALUES ('248', 'Puerto Octay', '39');
INSERT INTO "public"."comuna" VALUES ('249', 'Purranque', '39');
INSERT INTO "public"."comuna" VALUES ('250', 'Puyehue', '39');
INSERT INTO "public"."comuna" VALUES ('251', 'Río Negro', '39');
INSERT INTO "public"."comuna" VALUES ('252', 'San Juan de la Costa', '39');
INSERT INTO "public"."comuna" VALUES ('253', 'San Pablo', '39');
INSERT INTO "public"."comuna" VALUES ('254', 'Chaitén', '40');
INSERT INTO "public"."comuna" VALUES ('255', 'Futaleufú', '40');
INSERT INTO "public"."comuna" VALUES ('256', 'Hualaihué', '40');
INSERT INTO "public"."comuna" VALUES ('257', 'Palena', '40');
INSERT INTO "public"."comuna" VALUES ('258', 'Coyhaique', '41');
INSERT INTO "public"."comuna" VALUES ('259', 'Lago Verde', '41');
INSERT INTO "public"."comuna" VALUES ('260', 'Aysén', '42');
INSERT INTO "public"."comuna" VALUES ('261', 'Cisnes', '42');
INSERT INTO "public"."comuna" VALUES ('262', 'Guaitecas', '42');
INSERT INTO "public"."comuna" VALUES ('263', 'Cochrane', '43');
INSERT INTO "public"."comuna" VALUES ('264', 'O''Higgins', '43');
INSERT INTO "public"."comuna" VALUES ('265', 'Tortel', '43');
INSERT INTO "public"."comuna" VALUES ('266', 'Chile Chico', '44');
INSERT INTO "public"."comuna" VALUES ('267', 'Río Ibáñez', '44');
INSERT INTO "public"."comuna" VALUES ('268', 'Punta Arenas', '45');
INSERT INTO "public"."comuna" VALUES ('269', 'Laguna Blanca', '45');
INSERT INTO "public"."comuna" VALUES ('270', 'Río Verde', '45');
INSERT INTO "public"."comuna" VALUES ('271', 'San Gregorio', '45');
INSERT INTO "public"."comuna" VALUES ('272', 'Cabo de Hornos', '46');
INSERT INTO "public"."comuna" VALUES ('273', 'Antártica', '46');
INSERT INTO "public"."comuna" VALUES ('274', 'Porvenir', '47');
INSERT INTO "public"."comuna" VALUES ('275', 'Primavera', '47');
INSERT INTO "public"."comuna" VALUES ('276', 'Timaukel', '47');
INSERT INTO "public"."comuna" VALUES ('277', 'Natales', '48');
INSERT INTO "public"."comuna" VALUES ('278', 'Torres del Paine', '48');
INSERT INTO "public"."comuna" VALUES ('279', 'Santiago', '49');
INSERT INTO "public"."comuna" VALUES ('280', 'Cerrillos', '49');
INSERT INTO "public"."comuna" VALUES ('281', 'Cerro Navia', '49');
INSERT INTO "public"."comuna" VALUES ('282', 'Conchalí', '49');
INSERT INTO "public"."comuna" VALUES ('283', 'El Bosque', '49');
INSERT INTO "public"."comuna" VALUES ('284', 'Estación Central', '49');
INSERT INTO "public"."comuna" VALUES ('285', 'Huechuraba', '49');
INSERT INTO "public"."comuna" VALUES ('286', 'Independencia', '49');
INSERT INTO "public"."comuna" VALUES ('287', 'La Cisterna', '49');
INSERT INTO "public"."comuna" VALUES ('288', 'La Florida', '49');
INSERT INTO "public"."comuna" VALUES ('289', 'La Granja', '49');
INSERT INTO "public"."comuna" VALUES ('290', 'La Pintana', '49');
INSERT INTO "public"."comuna" VALUES ('291', 'La Reina', '49');
INSERT INTO "public"."comuna" VALUES ('292', 'Las Condes', '49');
INSERT INTO "public"."comuna" VALUES ('293', 'Lo Barnechea', '49');
INSERT INTO "public"."comuna" VALUES ('294', 'Lo Espejo', '49');
INSERT INTO "public"."comuna" VALUES ('295', 'Lo Prado', '49');
INSERT INTO "public"."comuna" VALUES ('296', 'Macul', '49');
INSERT INTO "public"."comuna" VALUES ('297', 'Maipú', '49');
INSERT INTO "public"."comuna" VALUES ('298', 'Ñuñoa', '49');
INSERT INTO "public"."comuna" VALUES ('299', 'Pedro Aguirre Cerda', '49');
INSERT INTO "public"."comuna" VALUES ('300', 'Peñalolén', '49');
INSERT INTO "public"."comuna" VALUES ('301', 'Providencia', '49');
INSERT INTO "public"."comuna" VALUES ('302', 'Pudahuel', '49');
INSERT INTO "public"."comuna" VALUES ('303', 'Quilicura', '49');
INSERT INTO "public"."comuna" VALUES ('304', 'Quinta Normal', '49');
INSERT INTO "public"."comuna" VALUES ('305', 'Recoleta', '49');
INSERT INTO "public"."comuna" VALUES ('306', 'Renca', '49');
INSERT INTO "public"."comuna" VALUES ('307', 'San Joaquín', '49');
INSERT INTO "public"."comuna" VALUES ('308', 'San Miguel', '49');
INSERT INTO "public"."comuna" VALUES ('309', 'San Ramón', '49');
INSERT INTO "public"."comuna" VALUES ('310', 'Vitacura', '49');
INSERT INTO "public"."comuna" VALUES ('311', 'Puente Alto', '50');
INSERT INTO "public"."comuna" VALUES ('312', 'Pirque', '50');
INSERT INTO "public"."comuna" VALUES ('313', 'San José de Maipo', '50');
INSERT INTO "public"."comuna" VALUES ('314', 'Colina', '51');
INSERT INTO "public"."comuna" VALUES ('315', 'Lampa', '51');
INSERT INTO "public"."comuna" VALUES ('316', 'Tiltil', '51');
INSERT INTO "public"."comuna" VALUES ('317', 'San Bernardo', '52');
INSERT INTO "public"."comuna" VALUES ('318', 'Buin', '52');
INSERT INTO "public"."comuna" VALUES ('319', 'Calera de Tango', '52');
INSERT INTO "public"."comuna" VALUES ('320', 'Paine', '52');
INSERT INTO "public"."comuna" VALUES ('321', 'Melipilla', '53');
INSERT INTO "public"."comuna" VALUES ('322', 'Alhué', '53');
INSERT INTO "public"."comuna" VALUES ('323', 'Curacaví', '53');
INSERT INTO "public"."comuna" VALUES ('324', 'María Pinto', '53');
INSERT INTO "public"."comuna" VALUES ('325', 'San Pedro', '53');
INSERT INTO "public"."comuna" VALUES ('326', 'Talagante', '54');
INSERT INTO "public"."comuna" VALUES ('327', 'El Monte', '54');
INSERT INTO "public"."comuna" VALUES ('328', 'Isla de Maipo', '54');
INSERT INTO "public"."comuna" VALUES ('329', 'Padre Hurtado', '54');
INSERT INTO "public"."comuna" VALUES ('330', 'Peñaflor', '54');
INSERT INTO "public"."comuna" VALUES ('331', 'Valdivia', '35');
INSERT INTO "public"."comuna" VALUES ('332', 'Corral', '35');
INSERT INTO "public"."comuna" VALUES ('333', 'Lanco', '35');
INSERT INTO "public"."comuna" VALUES ('334', 'Los Lagos', '35');
INSERT INTO "public"."comuna" VALUES ('335', 'Máfil', '35');
INSERT INTO "public"."comuna" VALUES ('336', 'Mariquina', '35');
INSERT INTO "public"."comuna" VALUES ('337', 'Paillaco', '35');
INSERT INTO "public"."comuna" VALUES ('338', 'Panguipulli', '35');
INSERT INTO "public"."comuna" VALUES ('339', 'La Unión', '36');
INSERT INTO "public"."comuna" VALUES ('340', 'Futrono', '36');
INSERT INTO "public"."comuna" VALUES ('341', 'Lago Ranco', '36');
INSERT INTO "public"."comuna" VALUES ('342', 'Río Bueno', '36');
INSERT INTO "public"."comuna" VALUES ('343', 'Arica', '1');
INSERT INTO "public"."comuna" VALUES ('344', 'Camarones', '1');
INSERT INTO "public"."comuna" VALUES ('345', 'Putre', '2');
INSERT INTO "public"."comuna" VALUES ('346', 'General Lagos', '2');

-- ----------------------------
-- Table structure for contacto
-- ----------------------------
DROP TABLE IF EXISTS "public"."contacto";
CREATE TABLE "public"."contacto" (
"id_contacto" int8 NOT NULL,
"direccion_contacto" varchar(128) COLLATE "default" NOT NULL,
"email_contacto" varchar(32) COLLATE "default" NOT NULL,
"nombre_contacto" varchar(64) COLLATE "default" NOT NULL,
"telefono_contacto" varchar(24) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of contacto
-- ----------------------------

-- ----------------------------
-- Table structure for entidad
-- ----------------------------
DROP TABLE IF EXISTS "public"."entidad";
CREATE TABLE "public"."entidad" (
"id_entidad" varchar(32) COLLATE "default" NOT NULL,
"direccion_entidad" varchar(64) COLLATE "default" NOT NULL,
"email_contacto" varchar(64) COLLATE "default" NOT NULL,
"nombre_contacto" varchar(64) COLLATE "default" NOT NULL,
"nombre_entidad" varchar(64) COLLATE "default" NOT NULL,
"telefono_contacto" varchar(64) COLLATE "default" NOT NULL,
"comuna_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of entidad
-- ----------------------------
INSERT INTO "public"."entidad" VALUES ('162726749', 'Rio Quillen 793', 'nsoza@gmail.com', 'Nataly Soza', 'Colegio Los Soza', '92458547', '250');
INSERT INTO "public"."entidad" VALUES ('171068053', 'Los Franciscanos 2945', 'tomas.cfin@gmail.com', 'Tomas Riquelme', 'Colegio Los Riquelme', '964402146', '311');

-- ----------------------------
-- Table structure for historial_reclamos
-- ----------------------------
DROP TABLE IF EXISTS "public"."historial_reclamos";
CREATE TABLE "public"."historial_reclamos" (
"id_historial_reclamos" int8 NOT NULL,
"fecha_historial" timestamp(6),
"area" int8,
"reclamo" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of historial_reclamos
-- ----------------------------
INSERT INTO "public"."historial_reclamos" VALUES ('2451', '2017-08-08 20:05:58.403', '4', '2');
INSERT INTO "public"."historial_reclamos" VALUES ('2501', '2017-08-08 20:09:18.96', '4', '3');
INSERT INTO "public"."historial_reclamos" VALUES ('2502', '2017-08-08 20:09:34.124', '3', '3');
INSERT INTO "public"."historial_reclamos" VALUES ('2551', '2017-08-08 21:14:11.228', '3', '3');
INSERT INTO "public"."historial_reclamos" VALUES ('2552', '2017-08-08 21:14:23.431', '1', '1');
INSERT INTO "public"."historial_reclamos" VALUES ('2601', '2017-09-05 21:28:29.173', '2', '4');
INSERT INTO "public"."historial_reclamos" VALUES ('2602', '2017-09-05 21:33:50.953', '2', '4');
INSERT INTO "public"."historial_reclamos" VALUES ('2651', '2017-09-17 03:34:14.094', '1', '5');
INSERT INTO "public"."historial_reclamos" VALUES ('2701', '2017-09-17 04:44:16.609', '1', '6');
INSERT INTO "public"."historial_reclamos" VALUES ('2751', '2017-09-17 04:49:02.87', '1', '7');

-- ----------------------------
-- Table structure for movimiento_series
-- ----------------------------
DROP TABLE IF EXISTS "public"."movimiento_series";
CREATE TABLE "public"."movimiento_series" (
"id_movimiento_series" int8 NOT NULL,
"detalle_movimiento" varchar(255) COLLATE "default",
"email_contacto" varchar(64) COLLATE "default",
"fecha_movimiento" timestamp(6),
"nombre_contacto" varchar(64) COLLATE "default",
"telefono_contacto" varchar(64) COLLATE "default",
"tipo_movimiento" varchar(255) COLLATE "default",
"usos" int4,
"id_entidad" varchar(32) COLLATE "default",
"numero_de_serie" varchar(64) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of movimiento_series
-- ----------------------------
INSERT INTO "public"."movimiento_series" VALUES ('2351', 'pruebas', null, '2017-07-01 18:20:28.241', 'test', null, 'Activacion', '50', '171068053', 'serie01');
INSERT INTO "public"."movimiento_series" VALUES ('2352', 'pruebas', null, '2017-07-01 18:20:52.935', 'test', null, 'Ampliacion', '50', '171068053', 'serie01');
INSERT INTO "public"."movimiento_series" VALUES ('2401', 'pruebas', null, '2017-08-08 16:55:47.061', 'test', null, 'Ampliacion', '150', '171068053', 'serie01');

-- ----------------------------
-- Table structure for numero_de_series
-- ----------------------------
DROP TABLE IF EXISTS "public"."numero_de_series";
CREATE TABLE "public"."numero_de_series" (
"id" varchar(64) COLLATE "default" NOT NULL,
"activado" bool NOT NULL,
"anulado_por_reinstalacion" bool NOT NULL,
"fecha_ingreso" timestamp(6),
"usos" int4,
"id_entidad" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of numero_de_series
-- ----------------------------
INSERT INTO "public"."numero_de_series" VALUES ('serie01', 't', 'f', '2017-07-01 00:00:00', '250', '171068053');
INSERT INTO "public"."numero_de_series" VALUES ('serie02', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie03', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie04', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie05', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie06', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie07', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie08', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie09', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie10', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie11', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie12', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie13', 'f', 'f', '2017-07-01 00:00:00', '0', null);
INSERT INTO "public"."numero_de_series" VALUES ('serie14', 'f', 'f', '2017-07-01 00:00:00', '0', null);

-- ----------------------------
-- Table structure for provincia
-- ----------------------------
DROP TABLE IF EXISTS "public"."provincia";
CREATE TABLE "public"."provincia" (
"provincia_id" int4 NOT NULL,
"provincia_nombre" varchar(23) COLLATE "default" NOT NULL,
"region_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of provincia
-- ----------------------------
INSERT INTO "public"."provincia" VALUES ('1', 'Arica', '15');
INSERT INTO "public"."provincia" VALUES ('2', 'Parinacota', '15');
INSERT INTO "public"."provincia" VALUES ('3', 'Iquique', '1');
INSERT INTO "public"."provincia" VALUES ('4', 'Tamarugal', '1');
INSERT INTO "public"."provincia" VALUES ('5', 'Antofagasta', '2');
INSERT INTO "public"."provincia" VALUES ('6', 'El Loa', '2');
INSERT INTO "public"."provincia" VALUES ('7', 'Tocopilla', '2');
INSERT INTO "public"."provincia" VALUES ('8', 'Copiapó', '3');
INSERT INTO "public"."provincia" VALUES ('9', 'Chañaral', '3');
INSERT INTO "public"."provincia" VALUES ('10', 'Huasco', '3');
INSERT INTO "public"."provincia" VALUES ('11', 'Elqui', '4');
INSERT INTO "public"."provincia" VALUES ('12', 'Choapa', '4');
INSERT INTO "public"."provincia" VALUES ('13', 'Limarí', '4');
INSERT INTO "public"."provincia" VALUES ('14', 'Valparaíso', '5');
INSERT INTO "public"."provincia" VALUES ('15', 'Isla de Pascua', '5');
INSERT INTO "public"."provincia" VALUES ('16', 'Los Andes', '5');
INSERT INTO "public"."provincia" VALUES ('17', 'Petorca', '5');
INSERT INTO "public"."provincia" VALUES ('18', 'Quillota', '5');
INSERT INTO "public"."provincia" VALUES ('19', 'San Antonio', '5');
INSERT INTO "public"."provincia" VALUES ('20', 'San Felipe de Aconcagua', '5');
INSERT INTO "public"."provincia" VALUES ('21', 'Marga Marga', '5');
INSERT INTO "public"."provincia" VALUES ('22', 'Cachapoal', '6');
INSERT INTO "public"."provincia" VALUES ('23', 'Cardenal Caro', '6');
INSERT INTO "public"."provincia" VALUES ('24', 'Colchagua', '6');
INSERT INTO "public"."provincia" VALUES ('25', 'Talca', '7');
INSERT INTO "public"."provincia" VALUES ('26', 'Cauquenes', '7');
INSERT INTO "public"."provincia" VALUES ('27', 'Curicó', '7');
INSERT INTO "public"."provincia" VALUES ('28', 'Linares', '7');
INSERT INTO "public"."provincia" VALUES ('29', 'Concepción', '8');
INSERT INTO "public"."provincia" VALUES ('30', 'Arauco', '8');
INSERT INTO "public"."provincia" VALUES ('31', 'Biobío', '8');
INSERT INTO "public"."provincia" VALUES ('32', 'Ñuble', '8');
INSERT INTO "public"."provincia" VALUES ('33', 'Cautín', '9');
INSERT INTO "public"."provincia" VALUES ('34', 'Malleco', '9');
INSERT INTO "public"."provincia" VALUES ('35', 'Valdivia', '14');
INSERT INTO "public"."provincia" VALUES ('36', 'Ranco', '14');
INSERT INTO "public"."provincia" VALUES ('37', 'Llanquihue', '10');
INSERT INTO "public"."provincia" VALUES ('38', 'Chiloé', '10');
INSERT INTO "public"."provincia" VALUES ('39', 'Osorno', '10');
INSERT INTO "public"."provincia" VALUES ('40', 'Palena', '10');
INSERT INTO "public"."provincia" VALUES ('41', 'Coihaique', '11');
INSERT INTO "public"."provincia" VALUES ('42', 'Aisén', '11');
INSERT INTO "public"."provincia" VALUES ('43', 'Capitán Prat', '11');
INSERT INTO "public"."provincia" VALUES ('44', 'General Carrera', '11');
INSERT INTO "public"."provincia" VALUES ('45', 'Magallanes', '12');
INSERT INTO "public"."provincia" VALUES ('46', 'Antártica Chilena', '12');
INSERT INTO "public"."provincia" VALUES ('47', 'Tierra del Fuego', '12');
INSERT INTO "public"."provincia" VALUES ('48', 'Última Esperanza', '12');
INSERT INTO "public"."provincia" VALUES ('49', 'Santiago', '13');
INSERT INTO "public"."provincia" VALUES ('50', 'Cordillera', '13');
INSERT INTO "public"."provincia" VALUES ('51', 'Chacabuco', '13');
INSERT INTO "public"."provincia" VALUES ('52', 'Maipo', '13');
INSERT INTO "public"."provincia" VALUES ('53', 'Melipilla', '13');
INSERT INTO "public"."provincia" VALUES ('54', 'Talagante', '13');

-- ----------------------------
-- Table structure for reclamo
-- ----------------------------
DROP TABLE IF EXISTS "public"."reclamo";
CREATE TABLE "public"."reclamo" (
"id" int4 DEFAULT nextval('reclamo_id_seq'::regclass) NOT NULL,
"colorhexidecimalvalue" varchar(64) COLLATE "default" NOT NULL,
"detalle_reclamo" varchar(1024) COLLATE "default" NOT NULL,
"email_contacto" varchar(32) COLLATE "default" NOT NULL,
"estado_reclamo" int4 NOT NULL,
"fecha_reclamo" timestamp(6) NOT NULL,
"nombre_contacto" varchar(64) COLLATE "default" NOT NULL,
"numero_contacto" varchar(32) COLLATE "default" NOT NULL,
"prioridad" int4 NOT NULL,
"ruta_archivo" varchar(64) COLLATE "default" NOT NULL,
"tipo_reclamo" varchar(32) COLLATE "default" NOT NULL,
"area" int8,
"id_entidad" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of reclamo
-- ----------------------------
INSERT INTO "public"."reclamo" VALUES ('1', '#ee7351', 'asaS', 'tomas.cfin@gmail.com', '2', '2017-08-08 20:04:32.226', 'sadsada', '12321321', '1', '', '', '1', '162726749');
INSERT INTO "public"."reclamo" VALUES ('2', '#ee7351', 'derivar con historial reclamo', 'tomas.cfin@gmail.com', '2', '2017-08-08 20:05:55.197', 'Tomas', '12321321', '1', '', '', '4', '162726749');
INSERT INTO "public"."reclamo" VALUES ('3', '#ee7351', 'sadsad', 'tomas.cfin@gmail.com', '2', '2017-08-08 20:09:15.89', 'tomas', '213', '1', '', '', '3', '162726749');
INSERT INTO "public"."reclamo" VALUES ('4', '#ee7351', 'este reclamo avanzo', 'tomas.cfin@gmail.com', '2', '2017-09-05 21:28:25.175', 'Tomas', '964402146', '1', '', '', '2', '162726749');
INSERT INTO "public"."reclamo" VALUES ('5', '#ee7351', 'w1', 'tomas.cfin@gmail.com', '1', '2017-09-17 03:34:09.319', 'w', 'w', '1', '', '', '1', '171068053');
INSERT INTO "public"."reclamo" VALUES ('6', '#ee7351', 'asdsad', 'tomas.cfin@gmail.com', '1', '2017-09-17 04:44:13.533', 'asdsadasd', '21321', '1', '', '', '1', '162726749');
INSERT INTO "public"."reclamo" VALUES ('7', '#ee7351', 'n', 'tomas.cfin@gmail.com', '1', '2017-09-17 04:49:00.237', 'njknlkn', '89', '1', '', '', '1', '162726749');

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS "public"."region";
CREATE TABLE "public"."region" (
"region_id" int4 NOT NULL,
"iso_3166_2_cl" varchar(5) COLLATE "default" NOT NULL,
"region_nombre" varchar(50) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO "public"."region" VALUES ('1', 'CL-TA', 'Tarapacá');
INSERT INTO "public"."region" VALUES ('2', 'CL-AN', 'Antofagasta');
INSERT INTO "public"."region" VALUES ('3', 'CL-AT', 'Atacama');
INSERT INTO "public"."region" VALUES ('4', 'CL-CO', 'Coquimbo');
INSERT INTO "public"."region" VALUES ('5', 'CL-VS', 'Valparaíso');
INSERT INTO "public"."region" VALUES ('6', 'CL-LI', 'Región del Libertador Gral. Bernardo O’Higgins');
INSERT INTO "public"."region" VALUES ('7', 'CL-ML', 'Región del Maule');
INSERT INTO "public"."region" VALUES ('8', 'CL-BI', 'Región del Biobío');
INSERT INTO "public"."region" VALUES ('9', 'CL-AR', 'Región de la Araucanía');
INSERT INTO "public"."region" VALUES ('10', 'CL-LL', 'Región de Los Lagos');
INSERT INTO "public"."region" VALUES ('11', 'CL-AI', 'Región Aisén del Gral. Carlos Ibáñez del Campo');
INSERT INTO "public"."region" VALUES ('12', 'CL-MA', 'Región de Magallanes y de la Antártica Chilena');
INSERT INTO "public"."region" VALUES ('13', 'CL-RM', 'Región Metropolitana de Santiago');
INSERT INTO "public"."region" VALUES ('14', 'CL-LR', 'Región de Los Ríos');
INSERT INTO "public"."region" VALUES ('15', 'CL-AP', 'Arica y Parinacota');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
"id" int4 DEFAULT nextval('roles_id_seq'::regclass) NOT NULL,
"descripcion" varchar(50) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO "public"."roles" VALUES ('1', 'ADMIN');
INSERT INTO "public"."roles" VALUES ('2', 'USER');

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS "public"."sequence";
CREATE TABLE "public"."sequence" (
"seq_name" varchar(50) COLLATE "default" NOT NULL,
"seq_count" numeric(38)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO "public"."sequence" VALUES ('SEQ_GEN', '2850');

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS "public"."usuarios";
CREATE TABLE "public"."usuarios" (
"id_usuario" int4 DEFAULT nextval('usuarios_id_usuario_seq'::regclass) NOT NULL,
"apellidos" varchar(255) COLLATE "default",
"direccion" varchar(255) COLLATE "default",
"email" varchar(255) COLLATE "default",
"fecha_nac" date,
"nombres" varchar(255) COLLATE "default",
"password" varchar(255) COLLATE "default",
"sexo" char(1) COLLATE "default",
"telefono" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO "public"."usuarios" VALUES ('16272674', 'Soza Cortes', 'Rio Quillen 793', 'anflonatom@gmail.com', '1985-02-14', 'Nataly Stephanie', '123', 'F', '954521235');
INSERT INTO "public"."usuarios" VALUES ('17106805', 'Riquelme Millar', 'Los Franciscanos 2945', 'tomas.cfin@gmail.com', '1989-07-13', 'Tomas Ignacio', '123456', 'M', '964402146');

-- ----------------------------
-- Table structure for usuarios_has_roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."usuarios_has_roles";
CREATE TABLE "public"."usuarios_has_roles" (
"id_usuario" int4 NOT NULL,
"id_rol" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of usuarios_has_roles
-- ----------------------------
INSERT INTO "public"."usuarios_has_roles" VALUES ('17106805', '1');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."areas_id_seq" OWNED BY "areas"."id";
ALTER SEQUENCE "public"."reclamo_id_seq" OWNED BY "reclamo"."id";
ALTER SEQUENCE "public"."roles_id_seq" OWNED BY "roles"."id";
ALTER SEQUENCE "public"."usuarios_id_usuario_seq" OWNED BY "usuarios"."id_usuario";

-- ----------------------------
-- Primary Key structure for table agendamiento
-- ----------------------------
ALTER TABLE "public"."agendamiento" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table areas
-- ----------------------------
ALTER TABLE "public"."areas" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table comuna
-- ----------------------------
ALTER TABLE "public"."comuna" ADD PRIMARY KEY ("comuna_id");

-- ----------------------------
-- Primary Key structure for table contacto
-- ----------------------------
ALTER TABLE "public"."contacto" ADD PRIMARY KEY ("id_contacto");

-- ----------------------------
-- Primary Key structure for table entidad
-- ----------------------------
ALTER TABLE "public"."entidad" ADD PRIMARY KEY ("id_entidad");

-- ----------------------------
-- Primary Key structure for table historial_reclamos
-- ----------------------------
ALTER TABLE "public"."historial_reclamos" ADD PRIMARY KEY ("id_historial_reclamos");

-- ----------------------------
-- Primary Key structure for table movimiento_series
-- ----------------------------
ALTER TABLE "public"."movimiento_series" ADD PRIMARY KEY ("id_movimiento_series");

-- ----------------------------
-- Primary Key structure for table numero_de_series
-- ----------------------------
ALTER TABLE "public"."numero_de_series" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table provincia
-- ----------------------------
ALTER TABLE "public"."provincia" ADD PRIMARY KEY ("provincia_id");

-- ----------------------------
-- Primary Key structure for table reclamo
-- ----------------------------
ALTER TABLE "public"."reclamo" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table region
-- ----------------------------
ALTER TABLE "public"."region" ADD PRIMARY KEY ("region_id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sequence
-- ----------------------------
ALTER TABLE "public"."sequence" ADD PRIMARY KEY ("seq_name");

-- ----------------------------
-- Primary Key structure for table usuarios
-- ----------------------------
ALTER TABLE "public"."usuarios" ADD PRIMARY KEY ("id_usuario");

-- ----------------------------
-- Primary Key structure for table usuarios_has_roles
-- ----------------------------
ALTER TABLE "public"."usuarios_has_roles" ADD PRIMARY KEY ("id_usuario", "id_rol");

-- ----------------------------
-- Foreign Key structure for table "public"."agendamiento"
-- ----------------------------
ALTER TABLE "public"."agendamiento" ADD FOREIGN KEY ("id_entidad") REFERENCES "public"."entidad" ("id_entidad") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."comuna"
-- ----------------------------
ALTER TABLE "public"."comuna" ADD FOREIGN KEY ("provincia_id") REFERENCES "public"."provincia" ("provincia_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."entidad"
-- ----------------------------
ALTER TABLE "public"."entidad" ADD FOREIGN KEY ("comuna_id") REFERENCES "public"."comuna" ("comuna_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."historial_reclamos"
-- ----------------------------
ALTER TABLE "public"."historial_reclamos" ADD FOREIGN KEY ("reclamo") REFERENCES "public"."reclamo" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."historial_reclamos" ADD FOREIGN KEY ("area") REFERENCES "public"."areas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."movimiento_series"
-- ----------------------------
ALTER TABLE "public"."movimiento_series" ADD FOREIGN KEY ("id_entidad") REFERENCES "public"."entidad" ("id_entidad") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."movimiento_series" ADD FOREIGN KEY ("numero_de_serie") REFERENCES "public"."numero_de_series" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."numero_de_series"
-- ----------------------------
ALTER TABLE "public"."numero_de_series" ADD FOREIGN KEY ("id_entidad") REFERENCES "public"."entidad" ("id_entidad") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."provincia"
-- ----------------------------
ALTER TABLE "public"."provincia" ADD FOREIGN KEY ("region_id") REFERENCES "public"."region" ("region_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."reclamo"
-- ----------------------------
ALTER TABLE "public"."reclamo" ADD FOREIGN KEY ("area") REFERENCES "public"."areas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."reclamo" ADD FOREIGN KEY ("id_entidad") REFERENCES "public"."entidad" ("id_entidad") ON DELETE NO ACTION ON UPDATE NO ACTION;
