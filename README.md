# PrayerTime-App
</h1>
<p dir="auto">
<li> In the home screen, the app must fetch and show today's prayer times based on the user's location.</li>
<li> Change the selected day by clicking back and forward arrows with a limitation to today.</li>
<li> Store times locally to be shown if there was any problem with the network.</li>
<li> Showing times in 12-hour format.</li>
<li> Countdown for the next prayer time in the home screen.</li>
<li> Showing the current location title in the home screen.</li>
<li> Navigate to the map screen to show Kaaba marker and current location marker.</li>
<li> Showing Qibla direction line on the map screen.</li>
  <li> android widget for countdown </li>
<li> Change the live direction based on rotationVector sensor result .</li>
</p>



# App Screens :

<div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
  <img src="https://github.com/user-attachments/assets/225ee0ad-cd24-47e2-8ebe-4d184f2c8193" alt="Screenshot 1" width="180">
  <img src="https://github.com/user-attachments/assets/d7b2492b-4388-4b67-9e12-09a501cfeb46" alt="Screenshot 3" width="180">
  <img src="https://github.com/user-attachments/assets/55d4df61-7300-405e-83dd-7a356ccf7260" alt="Screenshot 4" width="180">
  <img src="https://github.com/user-attachments/assets/3aa66bd1-e2b5-4d9e-bbc7-9d962455d5ab" alt="Screenshot 2" width="180">
  <img src="https://github.com/user-attachments/assets/53945541-da72-4e2d-bb59-1d0154bdc48a" alt="Screenshot 4" width="180">

</div>

# not granted permission state :

<div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
  <img src="https://github.com/user-attachments/assets/765686ce-f6c7-41d6-85f0-c35e3f300861" alt="Screenshot 4" width="200">
   <img src="https://github.com/user-attachments/assets/39885a64-9730-485b-9c45-e746726a01f6" alt="Screenshot 4" width="200">

</div>

# network connection states :

<div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
  <img src="https://github.com/user-attachments/assets/50aafc30-c2ea-4178-96a6-8d42783a4ad9" alt="Screenshot 4" width="200">
  <img src="https://github.com/user-attachments/assets/2b62f3d2-de28-41ed-9ed8-4801c767742c" alt="Screenshot 4" width="200">

</div>

#  network connection problem + first enter so no caching in local database   :

<div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 10px;">
  <img src="https://github.com/user-attachments/assets/0db04246-0ca5-42ba-a282-2e77cf397b40" alt="Screenshot 4" width="200">
</div>


<p dir="auto">

# apk 
  <a href="https://drive.google.com/file/d/1HfZZ84hWzJLMBb7RdtGoAqlNOAOEKK_O/view?usp=sharing" target="_blank">Download from here </a>

# App-Arch
![Screenshot from 2023-06-20 17-41-23](https://github.com/user-attachments/assets/51628a9c-5f14-4768-992e-e15fe241d6e5)

# In-App architecture
![arch](https://github.com/ahmed-faroukk/AlalmiyaAlhura-Task/assets/72602749/a4a02bb5-58ca-4ac6-a9c6-153182644af5)

# Database serves as the single source of truth, and other parts of the app access it via the repository.

![Capture](https://github.com/user-attachments/assets/55715934-e1c9-4c19-9d45-b121a8fe3af0)


# UI + Unit  testing :
![Screenshot from 2023-06-20 17-48-24](https://github.com/user-attachments/assets/b1366bea-8386-4244-a823-a546aaa5392f)


# Note* 
<ul>
  <li>The UI is composed of XML and Compose </li>
  <li>Fade animation in the home screen.</li>
  <li>Single source of truth, applying separation of concerns, OOP principles, and clean code practices.</li>
</ul>

# Tools & APIs
<ul>
  <li>MVVM Arch</li>
  <li>Clean architecture</li>
  <li>Dependency injection with dager Hilt</li>
  <li>Fused Location Provider API in Android</li>
  <li>Room DB</li>
  <li>Open street maps</li>
  <li>Unit Testing</li>
  <li>UI Testing</li>
  <li>Retrofit</li>
  <li>OkHttp</li>
  <li>Sealed Classes</li>
  <li>Kotlin Coroutines</li>
  <li>Live Data</li>
  <li>flow</li>
  <li>compose</li>
  <li>Navigation component</li>
  <li>View binding</li>
  <li>leakCanary</li>
  <li>Observing your network connection with Flow</li>
  <li>SplashScreen</li>
</ul>

 
