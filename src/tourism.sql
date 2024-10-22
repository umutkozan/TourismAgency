PGDMP  /            	        |            tourism    16.3    16.3 H               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    16736    tourism    DATABASE     �   CREATE DATABASE tourism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE tourism;
                postgres    false            �            1259    25107    facility    TABLE     |  CREATE TABLE public.facility (
    facility_id integer NOT NULL,
    facility_hotel_id integer NOT NULL,
    facility_free_park boolean NOT NULL,
    facility_free_wifi boolean NOT NULL,
    facility_pool boolean NOT NULL,
    facility_gym boolean NOT NULL,
    facility_concierge boolean NOT NULL,
    facility_spa boolean NOT NULL,
    facility_room_service boolean NOT NULL
);
    DROP TABLE public.facility;
       public         heap    postgres    false            �            1259    25106    facility_facility_id_seq    SEQUENCE     �   CREATE SEQUENCE public.facility_facility_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.facility_facility_id_seq;
       public          postgres    false    216            
           0    0    facility_facility_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.facility_facility_id_seq OWNED BY public.facility.facility_id;
          public          postgres    false    215            �            1259    25270    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name character varying(100) NOT NULL,
    hotel_address character varying(255) NOT NULL,
    hotel_city character varying(50) NOT NULL,
    hotel_region character varying(50) NOT NULL,
    hotel_email character varying(100) NOT NULL,
    hotel_phone character varying(20) NOT NULL,
    hotel_stars character varying(7) NOT NULL,
    hotel_pension_type_id integer NOT NULL,
    hotel_facility_id integer NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25269    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    226                       0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    225            �            1259    25328    pension    TABLE     �  CREATE TABLE public.pension (
    pension_type_id integer NOT NULL,
    pension_type_name character varying(100) NOT NULL,
    pension_type_hotel_id integer,
    pension_type_ultra boolean,
    pension_type_hsd boolean,
    pension_type_breakfast boolean,
    pension_type_tam boolean,
    pension_type_yarim boolean,
    pension_type_just_bed boolean,
    pension_type_ahfc boolean
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    25327    pension_pension_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_pension_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.pension_pension_type_id_seq;
       public          postgres    false    233                       0    0    pension_pension_type_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.pension_pension_type_id_seq OWNED BY public.pension.pension_type_id;
          public          postgres    false    232            �            1259    25286    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    reservation_customer_name character varying(100) NOT NULL,
    reservation_customer_contact character varying(100) NOT NULL,
    reservation_check_in_date date NOT NULL,
    reservation_check_out_date date NOT NULL,
    reservation_total_price numeric(10,2) NOT NULL,
    reservation_guest_count_adult integer NOT NULL,
    reservation_guest_count_child integer NOT NULL,
    reservation_customer_email character varying(50) NOT NULL,
    reservation_customer_tc bigint NOT NULL,
    reservation_customer_note character varying NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25285    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    230                       0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    229            �            1259    25279    room    TABLE     c  CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_type character varying(50) NOT NULL,
    room_price_adult numeric(10,2) NOT NULL,
    room_price_child numeric(10,2) NOT NULL,
    room_stock integer NOT NULL,
    room_bed_count integer NOT NULL,
    room_size integer NOT NULL,
    room_has_tv boolean NOT NULL,
    room_has_minibar boolean NOT NULL,
    room_has_console boolean NOT NULL,
    room_has_safe boolean NOT NULL,
    room_has_projector boolean NOT NULL,
    room_season_type character varying(50),
    room_pension_type character varying(50)
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25150    room_pension_types    TABLE     �   CREATE TABLE public.room_pension_types (
    pension_type_id integer NOT NULL,
    pension_type_name character varying(50) NOT NULL
);
 &   DROP TABLE public.room_pension_types;
       public         heap    postgres    false            �            1259    25149 &   room_pension_types_pension_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_pension_types_pension_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.room_pension_types_pension_type_id_seq;
       public          postgres    false    220                       0    0 &   room_pension_types_pension_type_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.room_pension_types_pension_type_id_seq OWNED BY public.room_pension_types.pension_type_id;
          public          postgres    false    219            �            1259    25278    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    228                       0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    227            �            1259    25157    room_season_types    TABLE     z   CREATE TABLE public.room_season_types (
    season_id integer NOT NULL,
    season_name character varying(50) NOT NULL
);
 %   DROP TABLE public.room_season_types;
       public         heap    postgres    false            �            1259    25156    room_season_types_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_season_types_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.room_season_types_season_id_seq;
       public          postgres    false    222                       0    0    room_season_types_season_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.room_season_types_season_id_seq OWNED BY public.room_season_types.season_id;
          public          postgres    false    221            �            1259    25114 
   room_types    TABLE     y   CREATE TABLE public.room_types (
    room_type_id integer NOT NULL,
    room_type_name character varying(50) NOT NULL
);
    DROP TABLE public.room_types;
       public         heap    postgres    false            �            1259    25113    room_types_room_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_types_room_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.room_types_room_type_id_seq;
       public          postgres    false    218                       0    0    room_types_room_type_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.room_types_room_type_id_seq OWNED BY public.room_types.room_type_id;
          public          postgres    false    217            �            1259    25322    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false            �            1259    25206    users    TABLE     �   CREATE TABLE public.users (
    user_id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    user_name character varying(50) NOT NULL,
    user_pass character varying(255) NOT NULL,
    user_role character varying(20) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    231            �            1259    25205    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_user_id_seq;
       public          postgres    false    224                       0    0    users_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;
          public          postgres    false    223            C           2604    25110    facility facility_id    DEFAULT     |   ALTER TABLE ONLY public.facility ALTER COLUMN facility_id SET DEFAULT nextval('public.facility_facility_id_seq'::regclass);
 C   ALTER TABLE public.facility ALTER COLUMN facility_id DROP DEFAULT;
       public          postgres    false    216    215    216            H           2604    25273    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    226    225    226            K           2604    25331    pension pension_type_id    DEFAULT     �   ALTER TABLE ONLY public.pension ALTER COLUMN pension_type_id SET DEFAULT nextval('public.pension_pension_type_id_seq'::regclass);
 F   ALTER TABLE public.pension ALTER COLUMN pension_type_id DROP DEFAULT;
       public          postgres    false    233    232    233            J           2604    25289    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    230    229    230            I           2604    25282    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    227    228    228            E           2604    25153 "   room_pension_types pension_type_id    DEFAULT     �   ALTER TABLE ONLY public.room_pension_types ALTER COLUMN pension_type_id SET DEFAULT nextval('public.room_pension_types_pension_type_id_seq'::regclass);
 Q   ALTER TABLE public.room_pension_types ALTER COLUMN pension_type_id DROP DEFAULT;
       public          postgres    false    219    220    220            F           2604    25160    room_season_types season_id    DEFAULT     �   ALTER TABLE ONLY public.room_season_types ALTER COLUMN season_id SET DEFAULT nextval('public.room_season_types_season_id_seq'::regclass);
 J   ALTER TABLE public.room_season_types ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    221    222    222            D           2604    25117    room_types room_type_id    DEFAULT     �   ALTER TABLE ONLY public.room_types ALTER COLUMN room_type_id SET DEFAULT nextval('public.room_types_room_type_id_seq'::regclass);
 F   ALTER TABLE public.room_types ALTER COLUMN room_type_id DROP DEFAULT;
       public          postgres    false    218    217    218            �          0    25107    facility 
   TABLE DATA           �   COPY public.facility (facility_id, facility_hotel_id, facility_free_park, facility_free_wifi, facility_pool, facility_gym, facility_concierge, facility_spa, facility_room_service) FROM stdin;
    public          postgres    false    216   �[       �          0    25270    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_city, hotel_region, hotel_email, hotel_phone, hotel_stars, hotel_pension_type_id, hotel_facility_id) FROM stdin;
    public          postgres    false    226   >]                 0    25328    pension 
   TABLE DATA           �   COPY public.pension (pension_type_id, pension_type_name, pension_type_hotel_id, pension_type_ultra, pension_type_hsd, pension_type_breakfast, pension_type_tam, pension_type_yarim, pension_type_just_bed, pension_type_ahfc) FROM stdin;
    public          postgres    false    233   [^                  0    25286    reservation 
   TABLE DATA           a  COPY public.reservation (reservation_id, reservation_room_id, reservation_customer_name, reservation_customer_contact, reservation_check_in_date, reservation_check_out_date, reservation_total_price, reservation_guest_count_adult, reservation_guest_count_child, reservation_customer_email, reservation_customer_tc, reservation_customer_note) FROM stdin;
    public          postgres    false    230   }`       �          0    25279    room 
   TABLE DATA             COPY public.room (room_id, room_hotel_id, room_type, room_price_adult, room_price_child, room_stock, room_bed_count, room_size, room_has_tv, room_has_minibar, room_has_console, room_has_safe, room_has_projector, room_season_type, room_pension_type) FROM stdin;
    public          postgres    false    228   _a       �          0    25150    room_pension_types 
   TABLE DATA           P   COPY public.room_pension_types (pension_type_id, pension_type_name) FROM stdin;
    public          postgres    false    220   b       �          0    25157    room_season_types 
   TABLE DATA           C   COPY public.room_season_types (season_id, season_name) FROM stdin;
    public          postgres    false    222   5b       �          0    25114 
   room_types 
   TABLE DATA           B   COPY public.room_types (room_type_id, room_type_name) FROM stdin;
    public          postgres    false    218   Rb       �          0    25206    users 
   TABLE DATA           I   COPY public.users (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    224   ob                  0    0    facility_facility_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.facility_facility_id_seq', 125, true);
          public          postgres    false    215                       0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 71, true);
          public          postgres    false    225                       0    0    pension_pension_type_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.pension_pension_type_id_seq', 117, true);
          public          postgres    false    232                       0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 6, true);
          public          postgres    false    229                       0    0 &   room_pension_types_pension_type_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.room_pension_types_pension_type_id_seq', 1, false);
          public          postgres    false    219                       0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 44, true);
          public          postgres    false    227                       0    0    room_season_types_season_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.room_season_types_season_id_seq', 1, false);
          public          postgres    false    221                       0    0    room_types_room_type_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.room_types_room_type_id_seq', 1, false);
          public          postgres    false    217                       0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 14, true);
          public          postgres    false    231                       0    0    users_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);
          public          postgres    false    223            M           2606    25112    facility facility_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.facility
    ADD CONSTRAINT facility_pkey PRIMARY KEY (facility_id);
 @   ALTER TABLE ONLY public.facility DROP CONSTRAINT facility_pkey;
       public            postgres    false    216            W           2606    25277    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    226            ]           2606    25333    pension pension_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_type_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    233            [           2606    25293    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    230            Q           2606    25155 *   room_pension_types room_pension_types_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.room_pension_types
    ADD CONSTRAINT room_pension_types_pkey PRIMARY KEY (pension_type_id);
 T   ALTER TABLE ONLY public.room_pension_types DROP CONSTRAINT room_pension_types_pkey;
       public            postgres    false    220            Y           2606    25284    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    228            S           2606    25162 (   room_season_types room_season_types_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.room_season_types
    ADD CONSTRAINT room_season_types_pkey PRIMARY KEY (season_id);
 R   ALTER TABLE ONLY public.room_season_types DROP CONSTRAINT room_season_types_pkey;
       public            postgres    false    222            O           2606    25119    room_types room_types_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.room_types
    ADD CONSTRAINT room_types_pkey PRIMARY KEY (room_type_id);
 D   ALTER TABLE ONLY public.room_types DROP CONSTRAINT room_types_pkey;
       public            postgres    false    218            U           2606    25211    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    224            ^           2606    25307    hotel fk_facility    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT fk_facility FOREIGN KEY (hotel_facility_id) REFERENCES public.facility(facility_id);
 ;   ALTER TABLE ONLY public.hotel DROP CONSTRAINT fk_facility;
       public          postgres    false    216    226    4685            _           2606    25339    hotel fk_pension    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT fk_pension FOREIGN KEY (hotel_pension_type_id) REFERENCES public.pension(pension_type_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT fk_pension;
       public          postgres    false    226    4701    233            a           2606    25317    reservation fk_reservation_room    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_reservation_room FOREIGN KEY (reservation_room_id) REFERENCES public.room(room_id);
 I   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk_reservation_room;
       public          postgres    false    228    230    4697            `           2606    25312    room fk_room_hotel    FK CONSTRAINT     }   ALTER TABLE ONLY public.room
    ADD CONSTRAINT fk_room_hotel FOREIGN KEY (room_hotel_id) REFERENCES public.hotel(hotel_id);
 <   ALTER TABLE ONLY public.room DROP CONSTRAINT fk_room_hotel;
       public          postgres    false    228    4695    226            �   P  x�m�A��0D��a��6`ߥ���_��.��Qv#���m��ooP:��P�S	(y*�#��L({�Qr�y�Dn��RR�s�l�_�n�[�7�s�qF=�[����;�(��I�I߉�iaOF	TE�N�A�ѐ8��%� � � � W�� j���������W�Q��A��A� }�>I�4:it�>I��O�'Q��E�"j��ZD��E���(]���	W��dٓ�S�{�Ȳ'˞���CD�X�"�"�"�"�"ע�KM����ML#���)F��H<1{��bs�jn� �.Lqa���^ǥ	f�.���`����#@=sS̢�Mil��o��7���      �     x���Mj�0��3��	�F���.r�f��`'�n�֔@�B��r��ϵ	���D 1H�}zz�t�)ד8�I\Ve}��a_o�M�]�u�0"5��=O^;;s�<�ܱ)��PXMÑ�*Iı�$H D�ϒb5{7���ɲ���2F@(��A�˙�XbX�]��(�"����X��b[��M,KN��K۵��Ģ�����1���������O�e�>bu&E�� �_�����^J_2�gdr�E�m��2�'��'@           x����j�@F�3O1O4��.KK	�@�E ��V��bwZ��t�u���{5%R#�ft��Ƈ�˙�ʺZ����:O���_����Wo��v�����Gnħ���t�:�tW�Z�q��O���2���N�}���*̉�ۤ>����O�E΋�����N�]���E0��}>&q��e���a��N��H��h��������,@����]�4��b���,��+����b�	�T{�X��ڐJ���Z6>���Ϡ��<Be��Je�(�JD�&�Zb�%�["���w��� b�%�["�O�x2$���4D�!2MMf�&�?T
]~�iJƧ�%�-1n�qK�[b���ג�Z2�d����tD�#2��LGd�u�Y�u�Yz_O�{�/EdFtv�qRɓ�<zأۑx�OFʓ�jI�PK���H
1��@d"3O��.�i��SD�B<E�)O�D%���d�t�^*�@:�����g��ְ�4�C��S�-T��1ϩ�#�S�z{g[���9R��"��WV���]�T���3)�_\�.          �   x�u�Aj�@E�N��H�x�K)���)�f׍	n0�vI�@r�"��t��� ��/1<6��,j!$��TI�q,�S%�������o�}��h���x��]�w�cs0;8�C�'�/Cu�/�䠙_���;���&�������6_�!w�l�[1�������zY�z'I1~��d�@�a�R?^/������|Ƙ_�0O      �   �   x����
�0�s�0��V�QX��Pc0��'���P�!������ǟB
��u����7�3D��>a^g���F��ގ���a��p��
U�4�$$�
�cMq淵U迒S~�u!�*�)V������剢��(ZI����Q9K���8���*����ʄu�@      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x�MM;�0 �_O�	-h\�v P`��ĥ�P	�{�L+X^^ޗ!y��VM��QZː\EZ�ޠx��\Ty�pN(*�ҝY��GԢ�av��<�n+�Qfe����!F5٧Q=(�v��쵕���Έ�PF
����סx��m���*�Y'��{BË��:ϓ"���w�,�B���O�     