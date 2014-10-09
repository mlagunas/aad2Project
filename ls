[1mdiff --git a/bin/AndroidManifest.xml b/bin/AndroidManifest.xml[m
[1mindex 2fe406a..324cd2d 100644[m
[1m--- a/bin/AndroidManifest.xml[m
[1m+++ b/bin/AndroidManifest.xml[m
[36m@@ -14,8 +14,15 @@[m
         android:label="@string/app_name"[m
         android:theme="@style/AppTheme" >[m
         <activity[m
[31m-            android:name=".MainActivity"[m
[32m+[m[32m            android:name="com.example.aad2project.ui.MainActivity"[m
             android:label="@string/app_name" >[m
[32m+[m[41m            [m
[32m+[m[32m        </activity>[m
[32m+[m[32m        <activity[m
[32m+[m[32m            android:name="com.example.aad2project.ui.ManagerActivity"[m
[32m+[m[32m            android:label="@string/title_activity_manager" >[m
[32m+[m[41m            [m
[32m+[m[32m            <!--  -->[m
             <intent-filter>[m
                 <action android:name="android.intent.action.MAIN" />[m
 [m
[1mdiff --git a/bin/R.txt b/bin/R.txt[m
[1mindex d439aa2..c2a39cd 100644[m
[1m--- a/bin/R.txt[m
[1m+++ b/bin/R.txt[m
[36m@@ -235,6 +235,8 @@[m [mint drawable abc_textfield_searchview_holo_light 0x7f020054[m
 int drawable abc_textfield_searchview_right_holo_dark 0x7f020055[m
 int drawable abc_textfield_searchview_right_holo_light 0x7f020056[m
 int drawable ic_launcher 0x7f020057[m
[32m+[m[32mint drawable testimagegs 0x7f020058[m
[32m+[m[32mint id accountCreation 0x7f050046[m
 int id action_bar 0x7f05001c[m
 int id action_bar_activity_content 0x7f050015[m
 int id action_bar_container 0x7f05001b[m
[36m@@ -246,19 +248,21 @@[m [mint id action_context_bar 0x7f05001d[m
 int id action_menu_divider 0x7f050016[m
 int id action_menu_presenter 0x7f050017[m
 int id action_mode_close_button 0x7f050024[m
[31m-int id action_settings 0x7f050041[m
[32m+[m[32mint id action_settings 0x7f05004a[m
 int id activity_chooser_view_content 0x7f050025[m
 int id always 0x7f05000b[m
 int id beginning 0x7f050011[m
 int id button1 0x7f05003f[m
 int id checkbox 0x7f05002d[m
 int id collapseActionView 0x7f05000d[m
[32m+[m[32mint id confimrpasswordCreationAccount 0x7f050049[m
 int id default_activity_button 0x7f050028[m
 int id dialog 0x7f05000e[m
 int id disableHome 0x7f050008[m
 int id dropdown 0x7f05000f[m
 int id edit_query 0x7f050030[m
 int id email 0x7f05003d[m
[32m+[m[32mint id emailCreationAccount 0x7f050047[m
 int id end 0x7f050013[m
 int id expand_activities_button 0x7f050026[m
 int id expanded_menu 0x7f05002c[m
[36m@@ -268,6 +272,8 @@[m [mint id homeAsUp 0x7f050005[m
 int id icon 0x7f05002a[m
 int id ifRoom 0x7f05000a[m
 int id image 0x7f050027[m
[32m+[m[32mint id imageView1 0x7f050043[m
[32m+[m[32mint id linearLayout1 0x7f050042[m
 int id listMode 0x7f050001[m
 int id list_item 0x7f050029[m
 int id middle 0x7f050012[m
[36m@@ -275,7 +281,9 @@[m [mint id never 0x7f050009[m
 int id newAccount 0x7f050040[m
 int id none 0x7f050010[m
 int id normal 0x7f050000[m
[32m+[m[32mint id pager 0x7f050041[m
 int id password 0x7f05003e[m
[32m+[m[32mint id passwordCreationAccount 0x7f050048[m
 int id progress_circular 0x7f050018[m
 int id progress_horizontal 0x7f050019[m
 int id radio 0x7f05002f[m
[36m@@ -296,6 +304,8 @@[m [mint id showTitle 0x7f050006[m
 int id split_action_bar 0x7f05001e[m
 int id submit_area 0x7f050039[m
 int id tabMode 0x7f050002[m
[32m+[m[32mint id textView1 0x7f050044[m
[32m+[m[32mint id textView2 0x7f050045[m
 int id title 0x7f05002b[m
 int id top_action_bar 0x7f050020[m
 int id up 0x7f050021[m
[36m@@ -327,9 +337,14 @@[m [mint layout abc_search_dropdown_item_icons_2line 0x7f030015[m
 int layout abc_search_view 0x7f030016[m
 int layout abc_simple_decor 0x7f030017[m
 int layout activity_main 0x7f030018[m
[31m-int layout support_simple_spinner_dropdown_item 0x7f030019[m
[32m+[m[32mint layout activity_manager 0x7f030019[m
[32m+[m[32mint layout activity_plant_information 0x7f03001a[m
[32m+[m[32mint layout fragment_account_creation 0x7f03001b[m
[32m+[m[32mint layout fragment_plant_manager 0x7f03001c[m
[32m+[m[32mint layout fragment_task_calendar 0x7f03001d[m
[32m+[m[32mint layout support_simple_spinner_dropdown_item 0x7f03001e[m
 int menu main 0x7f0c0000[m
[31m-int string Login 0x7f0a0012[m
[32m+[m[32mint menu manager 0x7f0c0001[m
 int string abc_action_bar_home_description 0x7f0a0001[m
 int string abc_action_bar_up_description 0x7f0a0002[m
 int string abc_action_menu_overflow_description 0x7f0a0003[m
[36m@@ -343,12 +358,22 @@[m [mint string abc_searchview_description_submit 0x7f0a0007[m
 int string abc_searchview_description_voice 0x7f0a0008[m
 int string abc_shareactionprovider_share_with 0x7f0a000c[m
 int string abc_shareactionprovider_share_with_application 0x7f0a000b[m
[32m+[m[32mint string accountCreationFragment 0x7f0a0016[m
 int string action_settings 0x7f0a000e[m
 int string app_name 0x7f0a000d[m
[32m+[m[32mint string confirmPasswordHint 0x7f0a0011[m
[32m+[m[32mint string create 0x7f0a0014[m
 int string emailHint 0x7f0a000f[m
[31m-int string greenhub 0x7f0a0011[m
[31m-int string newAccount 0x7f0a0013[m
[32m+[m[32mint string greenhub 0x7f0a0012[m
[32m+[m[32mint string hello_blank_fragment 0x7f0a001c[m
[32m+[m[32mint string hello_world 0x7f0a001b[m
[32m+[m[32mint string login 0x7f0a0013[m
[32m+[m[32mint string newAccount 0x7f0a0015[m
 int string passwordHint 0x7f0a0010[m
[32m+[m[32mint string title_activity_manager 0x7f0a0017[m
[32m+[m[32mint string title_section1 0x7f0a0018[m
[32m+[m[32mint string title_section2 0x7f0a0019[m
[32m+[m[32mint string title_section3 0x7f0a001a[m
 int style AppBaseTheme 0x7f0b008b[m
 int style AppTheme 0x7f0b008c[m
 int style TextAppearance_AppCompat_Base_CompactMenu_Dialog 0x7f0b0063[m
[1mdiff --git a/bin/aad2Project.apk b/bin/aad2Project.apk[m
[1mindex 2a7549f..b1d69f7 100644[m
Binary files a/bin/aad2Project.apk and b/bin/aad2Project.apk differ
[1mdiff --git a/bin/classes.dex b/bin/classes.dex[m
[1mindex 3195488..e219558 100644[m
Binary files a/bin/classes.dex and b/bin/classes.dex differ
[1mdiff --git a/bin/classes/android/support/v7/appcompat/R$layout.class b/bin/classes/android/support/v7/appcompat/R$layout.class[m
[1mindex c0abaeb..ceb5aec 100644[m
Binary files a/bin/classes/android/support/v7/appcompat/R$layout.class and b/bin/classes/android/support/v7/appcompat/R$layout.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/MainActivity.class b/bin/classes/com/example/aad2project/MainActivity.class[m
[1mdeleted file mode 100644[m
[1mindex 90c3186..0000000[m
Binary files a/bin/classes/com/example/aad2project/MainActivity.class and /dev/null differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$drawable.class b/bin/classes/com/example/aad2project/R$drawable.class[m
[1mindex 08e3c19..0af3c8c 100644[m
Binary files a/bin/classes/com/example/aad2project/R$drawable.class and b/bin/classes/com/example/aad2project/R$drawable.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$id.class b/bin/classes/com/example/aad2project/R$id.class[m
[1mindex 665bb38..eb51049 100644[m
Binary files a/bin/classes/com/example/aad2project/R$id.class and b/bin/classes/com/example/aad2project/R$id.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$integer.class b/bin/classes/com/example/aad2project/R$integer.class[m
[1mindex 4ff2794..7771659 100644[m
Binary files a/bin/classes/com/example/aad2project/R$integer.class and b/bin/classes/com/example/aad2project/R$integer.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$layout.class b/bin/classes/com/example/aad2project/R$layout.class[m
[1mindex f3ce446..68d2d96 100644[m
Binary files a/bin/classes/com/example/aad2project/R$layout.class and b/bin/classes/com/example/aad2project/R$layout.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$menu.class b/bin/classes/com/example/aad2project/R$menu.class[m
[1mindex 30df3ee..d4e1727 100644[m
Binary files a/bin/classes/com/example/aad2project/R$menu.class and b/bin/classes/com/example/aad2project/R$menu.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$string.class b/bin/classes/com/example/aad2project/R$string.class[m
[1mindex f5c5e03..8fa544f 100644[m
Binary files a/bin/classes/com/example/aad2project/R$string.class and b/bin/classes/com/example/aad2project/R$string.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$style.class b/bin/classes/com/example/aad2project/R$style.class[m
[1mindex 62cd359..573fa66 100644[m
Binary files a/bin/classes/com/example/aad2project/R$style.class and b/bin/classes/com/example/aad2project/R$style.class differ
[1mdiff --git a/bin/classes/com/example/aad2project/R$styleable.class b/bin/classes/com/example/aad2project/R$styleable.class[m
[1mindex 3afc5be..ce14c33 100644[m
Binary files a/bin/classes/com/example/aad2project/R$styleable.class and b/bin/classes/com/example/aad2project/R$styleable.class differ
[1mdiff --git a/bin/dexedLibs/android-support-v4-1d76e7dc62a3e23a10beafa58e36c448.jar b/bin/dexedLibs/android-support-v4-1d76e7dc62a3e23a10beafa58e36c448.jar[m
[1mdeleted file mode 100644[m
[1mindex cd0bcfd..0000000[m
Binary files a/bin/dexedLibs/android-support-v4-1d76e7dc62a3e23a10beafa58e36c448.jar and /dev/null differ
[1mdiff --git a/bin/dexedLibs/android-support-v7-appcompat-ce90b24c590a127d99f3c3d6188bbf33.jar b/bin/dexedLibs/android-support-v7-appcompat-ce90b24c590a127d99f3c3d6188bbf33.jar[m
[1mdeleted file mode 100644[m
[1mindex edd7a96..0000000[m
Binary files a/bin/dexedLibs/android-support-v7-appcompat-ce90b24c590a127d99f3c3d6188bbf33.jar and /dev/null differ
[1mdiff --git a/bin/dexedLibs/appcompat_v7-fc9d746503dea53307afb97a1fc549a1.jar b/bin/dexedLibs/appcompat_v7-fc9d746503dea53307afb97a1fc549a1.jar[m
[1mdeleted file mode 100644[m
[1mindex d3fb981..0000000[m
Binary files a/bin/dexedLibs/appcompat_v7-fc9d746503dea53307afb97a1fc549a1.jar and /dev/null differ
[1mdiff --git a/bin/jarlist.cache b/bin/jarlist.cache[m
[1mindex ddc2387..bd06ecb 100644[m
[1m--- a/bin/jarlist.cache[m
[1m+++ b/bin/jarlist.cache[m
[36m@@ -1,5 +1,5 @@[m
 # cache for current jar dependency. DO NOT EDIT.[m
 # format is <lastModified> <length> <SHA-1> <path>[m
 # Encoding is UTF-8[m
[31m-1403536640000 758727 efec67655f6db90757faa37201efcee2a9ec3507 C:\Users\Toscan\workspace\appcompat_v7\libs\android-support-v4.jar[m
[31m-1412761514213 758727 efec67655f6db90757faa37201efcee2a9ec3507 C:\Users\Toscan\workspace\aad2Project\libs\android-support-v4.jar[m
[32m+[m[32m1412670137892 758727 efec67655f6db90757faa37201efcee2a9ec3507 M:\AarhusUniversity\aad2Project\libs\android-support-v4.jar[m
[32m+[m[32m1403536640000 758727 efec67655f6db90757faa37201efcee2a9ec3507 M:\AarhusUniversity\Smartphone_Apps\Workspace\appcompat_v7\libs\android-support-v4.jar[m
[1mdiff --git a/bin/resources.ap_ b/bin/resources.ap_[m
[1mindex b9b2dcd..f790609 100644[m
Binary files a/bin/resources.ap_ and b/bin/resources.ap_ differ
[1mdiff --git a/gen/android/support/v7/appcompat/R.java b/gen/android/support/v7/appcompat/R.java[m
[1mindex e5252e9..ffb43b6 100644[m
[1m--- a/gen/android/support/v7/appcompat/R.java[m
[1m+++ b/gen/android/support/v7/appcompat/R.java[m
[36m@@ -343,7 +343,7 @@[m [mpublic final class R {[m
 		public static final int abc_search_dropdown_item_icons_2line = 0x7f030015;[m
 		public static final int abc_search_view = 0x7f030016;[m
 		public static final int abc_simple_decor = 0x7f030017;[m
[31m-		public static final int support_simple_spinner_dropdown_item = 0x7f030019;[m
[32m+[m		[32mpublic static final int support_simple_spinner_dropdown_item = 0x7f03001e;[m
 	}[m
 	public static final class string {[m
 		public static final int abc_action_bar_home_description = 0x7f0a0001;[m
[1mdiff --git a/gen/com/example/aad2project/R.java b/gen/com/example/aad2project/R.java[m
[1mindex 227c49a..0e74fc5 100644[m
[1m--- a/gen/com/example/aad2project/R.java[m
[1m+++ b/gen/com/example/aad2project/R.java[m
[36m@@ -1078,8 +1078,10 @@[m [mcontaining a value of this type.[m
         public static final int abc_textfield_searchview_right_holo_dark=0x7f020055;[m
         public static final int abc_textfield_searchview_right_holo_light=0x7f020056;[m
         public static final int ic_launcher=0x7f020057;[m
[32m+[m[32m        public static final int testimagegs=0x7f020058;[m
     }[m
     public static final class id {[m
[32m+[m[32m        public static final int accountCreation=0x7f050046;[m
         public static final int action_bar=0x7f05001c;[m
         public static final int action_bar_activity_content=0x7f050015;[m
         public static final int action_bar_container=0x7f05001b;[m
[36m@@ -1091,19 +1093,21 @@[m [mcontaining a value of this type.[m
         public static final int action_menu_divider=0x7f050016;[m
         public static final int action_menu_presenter=0x7f050017;[m
         public static final int action_mode_close_button=0x7f050024;[m
[31m-        public static final int action_settings=0x7f050041;[m
[32m+[m[32m        public static final int action_settings=0x7f05004a;[m
         public static final int activity_chooser_view_content=0x7f050025;[m
         public static final int always=0x7f05000b;[m
         public static final int beginning=0x7f050011;[m
         public static final int button1=0x7f05003f;[m
         public static final int checkbox=0x7f05002d;[m
         public static final int collapseActionView=0x7f05000d;[m
[32m+[m[32m        public static final int confimrpasswordCreationAccount=0x7f050049;[m
         public static final int default_activity_button=0x7f050028;[m
         public static final int dialog=0x7f05000e;[m
         public static final int disableHome=0x7f050008;[m
         public static final int dropdown=0x7f05000f;[m
         public static final int edit_query=0x7f050030;[m
         public static final int email=0x7f05003d;[m
[32m+[m[32m        public static final int emailCreationAccount=0x7f050047;[m
         public static final int end=0x7f050013;[m
         public static final int expand_activities_button=0x7f050026;[m
         public static final int expanded_menu=0x7f05002c;[m
[36m@@ -1113,6 +1117,8 @@[m [mcontaining a value of this type.[m
         public static final int icon=0x7f05002a;[m
         public static final int ifRoom=0x7f05000a;[m
         public static final int image=0x7f050027;[m
[32m+[m[32m        public static final int imageView1=0x7f050043;[m
[32m+[m[32m        public static final int linearLayout1=0x7f050042;[m
         public static final int listMode=0x7f050001;[m
         public static final int list_item=0x7f050029;[m
         public static final int middle=0x7f050012;[m
[36m@@ -1120,7 +1126,9 @@[m [mcontaining a value of this type.[m
         public static final int newAccount=0x7f050040;[m
         public static final int none=0x7f050010;[m
         public static final int normal=0x7f050000;[m
[32m+[m[32m        public static final int pager=0x7f050041;[m
         public static final int password=0x7f05003e;[m
[32m+[m[32m        public static final int passwordCreationAccount=0x7f050048;[m
         public static final int progress_circular=0x7f050018;[m
         public static final int progress_horizontal=0x7f050019;[m
         public static final int radio=0x7f05002f;[m
[36m@@ -1141,6 +1149,8 @@[m [mcontaining a value of this type.[m
         public static final int split_action_bar=0x7f05001e;[m
         public static final int submit_area=0x7f050039;[m
         public static final int tabMode=0x7f050002;[m
[32m+[m[32m        public static final int textView1=0x7f050044;[m
[32m+[m[32m        public static final int textView2=0x7f050045;[m
         public static final int title=0x7f05002b;[m
         public static final int top_action_bar=0x7f050020;[m
         public static final int up=0x7f050021;[m
[36m@@ -1198,13 +1208,18 @@[m [mcontaining a value of this type.[m
         public static final int abc_search_view=0x7f030016;[m
         public static final int abc_simple_decor=0x7f030017;[m
         public static final int activity_main=0x7f030018;[m
[31m-        public static final int support_simple_spinner_dropdown_item=0x7f030019;[m
[32m+[m[32m        public static final int activity_manager=0x7f030019;[m
[32m+[m[32m        public static final int activity_plant_information=0x7f03001a;[m
[32m+[m[32m        public static final int fragment_account_creation=0x7f03001b;[m
[32m+[m[32m        public static final int fragment_plant_manager=0x7f03001c;[m
[32m+[m[32m        public static final int fragment_task_calendar=0x7f03001d;[m
[32m+[m[32m        public static final int support_simple_spinner_dropdown_item=0x7f03001e;[m
     }[m
     public static final class menu {[m
         public static final int main=0x7f0c0000;[m
[32m+[m[32m        public static final int manager=0x7f0c0001;[m
     }[m
     public static final class string {[m
[31m-        public static final int Login=0x7f0a0012;[m
         /**  Content description for the action bar "home" affordance. [CHAR LIMIT=NONE] [m
          */[m
         public static final int abc_action_bar_home_description=0x7f0a0001;[m
[36m@@ -1245,12 +1260,22 @@[m [mcontaining a value of this type.[m
         /**  Description of a share target (both in the list of such or the default share button) in a ShareActionProvider (share UI). [CHAR LIMIT=NONE] [m
          */[m
         public static final int abc_shareactionprovider_share_with_application=0x7f0a000b;[m
[32m+[m[32m        public static final int accountCreationFragment=0x7f0a0016;[m
         public static final int action_settings=0x7f0a000e;[m
         public static final int app_name=0x7f0a000d;[m
[32m+[m[32m        public static final int confirmPasswordHint=0x7f0a0011;[m
[32m+[m[32m        public static final int create=0x7f0a0014;[m
         public static final int emailHint=0x7f0a000f;[m
[31m-        public static final int greenhub=0x7f0a0011;[m
[31m-        public static final int newAccount=0x7f0a0013;[m
[32m+[m[32m        public static final int greenhub=0x7f0a0012;[m
[32m+[m[32m        public static final int hello_blank_fragment=0x7f0a001c;[m
[32m+[m[32m        public static final int hello_world=0x7f0a001b;[m
[32m+[m[32m        public static final int login=0x7f0a0013;[m
[32m+[m[32m        public static final int newAccount=0x7f0a0015;[m
         public static final int passwordHint=0x7f0a0010;[m
[32m+[m[32m        public static final int title_activity_manager=0x7f0a0017;[m
[32m+[m[32m        public static final int title_section1=0x7f0a0018;[m
[32m+[m[32m        public static final int title_section2=0x7f0a0019;[m
[32m+[m[32m        public static final int title_section3=0x7f0a001a;[m
     }[m
     public static final class style {[m
         /** [m
[1mdiff --git a/project.properties b/project.properties[m
[1mindex a2a812a..c779071 100644[m
[1m--- a/project.properties[m
[1m+++ b/project.properties[m
[36m@@ -12,4 +12,5 @@[m
 [m
 # Project target.[m
 target=android-19[m
[31m-android.library.reference.1=../../../../../workspace_GreenHub/appcompat_v7[m
[32m+[m[32mandroid.library.reference.1=..\\..\\..\\..\\..\\workspace_GreenHub\\appcompat_v7[m
[32m+[m[32mandroid.library.reference.2=../Smartphone_Apps/Workspace/appcompat_v7[m
warning: LF will be replaced by CRLF in gen/android/support/v7/appcompat/R.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in gen/com/example/aad2project/BuildConfig.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in bin/jarlist.cache.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in project.properties.
The file will have its original line endings in your working directory.
