PGDMP  0    9                |            tourism    16.3    16.3 =    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16736    tourism    DATABASE     �   CREATE DATABASE tourism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE tourism;
                postgres    false            �            1259    24603    hotel    TABLE     _  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    name character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    address character varying(255),
    email character varying(255),
    phone character varying(255),
    star_rating integer,
    region character varying(50),
    features text,
    pension_types text
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    24602    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    216            �           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    215            �            1259    24624    pension_type    TABLE     �   CREATE TABLE public.pension_type (
    pension_type_id integer NOT NULL,
    hotel_id integer,
    pension_type_name character varying(255)
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    24623     pension_type_pension_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_type_pension_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.pension_type_pension_type_id_seq;
       public          postgres    false    220            �           0    0     pension_type_pension_type_id_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.pension_type_pension_type_id_seq OWNED BY public.pension_type.pension_type_id;
          public          postgres    false    219            �            1259    24684    price    TABLE       CREATE TABLE public.price (
    price_id integer NOT NULL,
    room_id integer NOT NULL,
    pension_type character varying(50) NOT NULL,
    season character varying(50) NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL
);
    DROP TABLE public.price;
       public         heap    postgres    false            �            1259    24683    price_price_id_seq    SEQUENCE     �   CREATE SEQUENCE public.price_price_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.price_price_id_seq;
       public          postgres    false    228            �           0    0    price_price_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.price_price_id_seq OWNED BY public.price.price_id;
          public          postgres    false    227            �            1259    24658    reservation    TABLE     :  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer,
    customer_name character varying(255),
    customer_contact character varying(255),
    check_in_date date,
    check_out_date date,
    adult_count integer,
    child_count integer,
    total_price double precision
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    24657    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    224            �           0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    223            �            1259    24636    room    TABLE     �  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer,
    season_id integer,
    pension_type_id integer,
    room_type character varying(255),
    nightly_rate_adult double precision,
    nightly_rate_child double precision,
    stock integer,
    bed_count integer,
    square_meters integer,
    has_tv boolean,
    has_minibar boolean,
    has_console boolean,
    has_safe boolean,
    has_projector boolean
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    24635    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    222            �           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    221            �            1259    24612    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer,
    season_name character varying(255),
    start_date date,
    end_date date
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    24611    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    218            �           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    217            �            1259    24672    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    24671    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_user_id_seq;
       public          postgres    false    226            �           0    0    users_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;
          public          postgres    false    225            8           2604    24606    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    216    215    216            :           2604    24627    pension_type pension_type_id    DEFAULT     �   ALTER TABLE ONLY public.pension_type ALTER COLUMN pension_type_id SET DEFAULT nextval('public.pension_type_pension_type_id_seq'::regclass);
 K   ALTER TABLE public.pension_type ALTER COLUMN pension_type_id DROP DEFAULT;
       public          postgres    false    220    219    220            >           2604    24687    price price_id    DEFAULT     p   ALTER TABLE ONLY public.price ALTER COLUMN price_id SET DEFAULT nextval('public.price_price_id_seq'::regclass);
 =   ALTER TABLE public.price ALTER COLUMN price_id DROP DEFAULT;
       public          postgres    false    228    227    228            <           2604    24661    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    223    224    224            ;           2604    24639    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    221    222    222            9           2604    24615    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    217    218    218            =           2604    24675    users user_id    DEFAULT     n   ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    225    226    226            �          0    24603    hotel 
   TABLE DATA           z   COPY public.hotel (hotel_id, name, city, address, email, phone, star_rating, region, features, pension_types) FROM stdin;
    public          postgres    false    216   �I       �          0    24624    pension_type 
   TABLE DATA           T   COPY public.pension_type (pension_type_id, hotel_id, pension_type_name) FROM stdin;
    public          postgres    false    220   �L       �          0    24684    price 
   TABLE DATA           b   COPY public.price (price_id, room_id, pension_type, season, adult_price, child_price) FROM stdin;
    public          postgres    false    228   )M       �          0    24658    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, customer_name, customer_contact, check_in_date, check_out_date, adult_count, child_count, total_price) FROM stdin;
    public          postgres    false    224   FM       �          0    24636    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, season_id, pension_type_id, room_type, nightly_rate_adult, nightly_rate_child, stock, bed_count, square_meters, has_tv, has_minibar, has_console, has_safe, has_projector) FROM stdin;
    public          postgres    false    222   �M       �          0    24612    season 
   TABLE DATA           X   COPY public.season (season_id, hotel_id, season_name, start_date, end_date) FROM stdin;
    public          postgres    false    218   N       �          0    24672    users 
   TABLE DATA           B   COPY public.users (user_id, username, password, role) FROM stdin;
    public          postgres    false    226   [N                   0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 36, true);
          public          postgres    false    215                       0    0     pension_type_pension_type_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.pension_type_pension_type_id_seq', 5, true);
          public          postgres    false    219                       0    0    price_price_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.price_price_id_seq', 1, false);
          public          postgres    false    227                       0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 15, true);
          public          postgres    false    223                       0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 5, true);
          public          postgres    false    221                       0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 5, true);
          public          postgres    false    217                       0    0    users_user_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.users_user_id_seq', 17, true);
          public          postgres    false    225            @           2606    24610    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    216            D           2606    24629    pension_type pension_type_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (pension_type_id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    220            N           2606    24689    price price_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_pkey PRIMARY KEY (price_id);
 :   ALTER TABLE ONLY public.price DROP CONSTRAINT price_pkey;
       public            postgres    false    228            H           2606    24665    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    224            F           2606    24641    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    222            B           2606    24617    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    218            J           2606    24679    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    226            L           2606    24681    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    226            P           2606    24630 '   pension_type pension_type_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 Q   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_hotel_id_fkey;
       public          postgres    false    4672    220    216            U           2606    24690    price price_room_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.price
    ADD CONSTRAINT price_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(room_id);
 B   ALTER TABLE ONLY public.price DROP CONSTRAINT price_room_id_fkey;
       public          postgres    false    222    4678    228            T           2606    24666 $   reservation reservation_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(room_id);
 N   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_room_id_fkey;
       public          postgres    false    4678    222    224            Q           2606    24642    room room_hotel_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    4672    222    216            R           2606    24652    room room_pension_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pension_type_id_fkey FOREIGN KEY (pension_type_id) REFERENCES public.pension_type(pension_type_id);
 H   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pension_type_id_fkey;
       public          postgres    false    4676    220    222            S           2606    24647    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(season_id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    222    218    4674            O           2606    24618    season season_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 E   ALTER TABLE ONLY public.season DROP CONSTRAINT season_hotel_id_fkey;
       public          postgres    false    4672    216    218            �   O  x��Tˎ�8<���O���e{|��� 3��:,�K[�m�iP�7�����%KJ�i �&��b�X��'�`#]��<��,y$
�j�?�8+�63Dq�Ng�ES��g�M��W ��� �;�c�clZ��OW�(��#y"p���0��H��OQ�G���Fg�Aow�t�
ޗ���R�n�V�gt,���H�7�C�slR<���(�&+��l�G<�-��Z!𛼕~4F�=�b���|��VP)s���X+�H�L?])���u=3<�ܲlӬ�x��H�Q��	˄iXʎ��2�)����{R�+�<����������5�Վ4���|��r��rYt	��ߓH�iOb	�e'�{½<I}�ϖ��UL�1����
:��t�$�/��ŭ��E95j&q�^��^�Ս$~�1�e�lnF��I�!K��Yb�hrFI�uUJ���#��1zς�Ø����Ҫ�$s/��2c��Zû\�#��Q������c�'�n٥�Ou�_��q�]՟��J*:� w�l=�M�o����*����,!c�N���9�Rf��/>y0�tP-bb+qh���J~sqE��c�^f땰R�"�b9/b�$�i\ak�궲�!e.�~a��`�ݺ�)��V<�)�l�km}��p�N_$ߣ�C�X�7S�6��R��k��a���T�bT��"�8��I�;��
��"��Ͷ3{g���d�U�LY�'�F;�aKg��4����Q�M�[�ty��f�p����y2�#4w�VZq�c���Qٚ{7�����p�~�So����'�~��A���Q���}��N�5o/���ػ��N&�� �(J�      �   $   x�3�4�t+��Qp�O,J�2r=sҠ�=... ���      �      x������ � �      �   `   x�34�4�t.-.��M-R0�L��+IL.1tH�H�-�I�K���4202�50�50�3��������Ј�a��#�f�"� 2���p��qqq ��#(      �   E   x�3�4��̼��T����\NCNS 72�,�4�2	 �K~i\1P��)�P�Ym� ���      �   @   x�3�4�.��M-R0202��f��P����!�PUxf^	�*C#�*S]#]#�=... ­8      �   �   x�E��
�0Eg�cJ�GJ��.�@:v1D��!N(�ׇ$��s�.���I��N��O.����e����P�+�e��tdq�a�_ !a��4����#���9����������8ݎ��J��Y�h@\�ʽ�7R[L�     