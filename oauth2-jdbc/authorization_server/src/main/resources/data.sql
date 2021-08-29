INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('client1', 'secret1', 'read',
	'client_credentials,refresh_token', null, null, 36000, 36000, null, TRUE);
	
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('rs1', 'rssecret1', 'read,write',
	'client_credentials', null, null, 36000, 36000, null, TRUE);
		
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('rs2', 'rssecret2', 'read',
	'client_credentials', null, null, 36000, 36000, null, TRUE);