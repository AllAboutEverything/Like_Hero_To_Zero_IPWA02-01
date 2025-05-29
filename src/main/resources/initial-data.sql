INSERT INTO CO2EMISSION (country, emission_year, emission_kt, approved) VALUES
                                                                  ('Germany', 2024, 7026317, false),
                                                                  ('United States', 2024, 62415021, false),
                                                                  ('China', 2024, 172318521, false);;

INSERT INTO scientist (username, password, role) VALUES
('admin', '$2a$10$SMitNl3xXiVzm8WLcWc.BOtAxzC68KsPlCkcRD6mVvh.mD.80UiCi', 'ROLE_ADMIN'), -- Passwort: "admin"
('scientist1', '$2a$10$uOEfsmUL.NgXKFt9xbRT0.H.k7z9tbym5pqYrYV8WSBYdGZebrd7W', 'ROLE_USER'), -- Passwort: "password"
('scientist2', '$2a$10$uOEfsmUL.NgXKFt9xbRT0.H.k7z9tbym5pqYrYV8WSBYdGZebrd7W', 'ROLE_USER'); -- Passwort: "password"
