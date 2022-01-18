-- Sample data for users
INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (1, 'Tarık', null, 'COŞKUN', '35098890776', 'MALE', 'tarikcoskun@hotmail.com.tr', '05548960682', '1234trk1234',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (2, 'Ali', 'Can', 'KELEŞ', '35716498265', 'MALE', 'alikeles@hotmail.com', '05356987416', '159gt74t1g5',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (3, 'Beyza', null, 'ÇELİK', '34927168246', 'FEMALE', 'beyzac@outlook.com.tr', '05421548937', '5df4df4fd5',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (4, 'Elif', null, 'KATİP', '35194658574', 'FEMALE', 'katipelif@gmail.com', '05563178965', '2teleif8522g',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (5, 'Ahmet', 'Hasan', 'ASLAN', '35174554996', 'MALE', 'aaslan@yahoo.com', '05555556987', '5r8ffffşg5',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (6, 'Kerem', null, 'KİRAZ', '35124166952', 'MALE', 'keremkiraz@hotmail.com.tr', '05486587215', '54s65f4s65df',
        '2021-12-19 01:23:13.001');

INSERT INTO public.users(id, first_name, middle_name, last_name, tc_id_number, gender, email, phone_number, password,
                         registration_date)
VALUES (7, 'Seray', 'Sevgi', 'AKYOL', '34261873954', 'FEMALE', 'serayyyakyol@hotmail.com', '05386649987', '6f85g4sdg',
        '2021-12-19 01:23:13.001');

alter sequence user_id_seq restart with 8;


-- Sample data for collections
INSERT INTO public.collections(id, creation_date)
VALUES (1, '2022-01-11 11:23:13.001');

INSERT INTO public.collections(id, creation_date)
VALUES (2, '2022-01-01 11:23:13.001');

INSERT INTO public.collections(id, creation_date)
VALUES (3, '2022-02-10 05:23:13.001');

alter sequence collection_id_seq restart with 4;


-- Sample data for loans
INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (1, 100000, 100000, 'MAIN', '2022-12-19 23:59:59.999', '2021-12-19 01:23:13.001', null, 3, null); -- Borcun süresi devam ediyor.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (2, 25000, 0, 'MAIN', '2021-03-01 23:59:59.999', '2020-10-03 09:23:13.001', null, 1, 1); -- Borcun süresi geçmiş, ödenmiş. Faizi aşağıda.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (3, 5000, 0, 'INTEREST', '2022-01-11 23:59:59.999', '2022-01-11 11:23:13.001', 2, 1, 1); -- Yukarıdaki borcun faizi.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (4, 300000, 300000, 'MAIN', '2022-01-01 23:59:59.999', '2021-01-01 13:23:13.001', null, 5, null); -- Borcun süresi geçmiş, ödenmemiş.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (5, 55000, 55000, 'MAIN', '2023-01-17 23:59:59.999', '2022-01-17 13:23:13.001', null, 5, null); -- Borcun süresi devam ediyor.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (6, 125000, 0, 'MAIN', '2021-11-01 23:59:59.999', '2021-05-05 09:23:13.001', null, 5, 2); -- Borcun süresi geçmiş, ödenmiş. Faizi aşağıda.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (7, 11000, 0, 'INTEREST', '2022-01-01 23:59:59.999', '2022-01-01 11:23:13.001', 6, 5, 2); -- Yukarıdaki borcun faizi.

INSERT INTO public.loans(id, main_amount, remaining_amount, type, maturity_date, creation_date, super_loan_id, user_id,
                         collection_id)
VALUES (8, 226000, 0, 'MAIN', '2022-02-15 23:59:59.999', '2021-02-15 05:23:13.001', null, 3, 3); -- Borç zamanında veya önceden ödenmiş.

alter sequence loan_id_seq restart with 9;