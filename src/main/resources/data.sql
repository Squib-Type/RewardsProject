DELETE FROM t_reward_result;
DELETE FROM t_fan_preference;
DELETE FROM t_seat;
DELETE FROM t_stand;


INSERT INTO t_stand (stand_id, available_seats, discount_price) VALUES
('East', 3, 10.00),
('West', 3, 5.00),
('South', 5, 2.00),
('North', 5, 2.00);

INSERT INTO t_seat (seat_id, stand_id) VALUES
('E001', 'East'),
('E002', 'East'),
('E003', 'East');

INSERT INTO t_seat (seat_id, stand_id) VALUES
('W501', 'West'),
('W502', 'West'),
('W503', 'West');

INSERT INTO t_seat (seat_id, stand_id) VALUES
('S101', 'South'),
('S102', 'South'),
('S103', 'South'),
('S104', 'South'),
('S105', 'South');

INSERT INTO t_seat (seat_id, stand_id) VALUES
('N601', 'North'),
('N602', 'North'),
('N603', 'North'),
('N604', 'North'),
('N605', 'North');