<template>
    <form>
    <md-card class="md-layout-item md-small-size-75 md-size-50">
      <md-card-header data-background-color="blue">
        <h2 class="title">Login</h2>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-100">
            <md-field>
              <label>Username</label>
              <md-input name="username" v-model="input.username" type="text"></md-input>
            </md-field>
            <md-field>
              <label>Password</label>
              <md-input name ="password" v-model="input.password" type="password"></md-input>
            </md-field>
            <md-button name ="login" class="md-raised md-info" @click="login()">Log In</md-button>
          </div>          
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
export default ({
    data: function() { 
      return {
        input: {
            username: "",
            password: ""
        },
        auth: this.authenticated
      };
    },
    methods: {
      login() {
        if (this.input.username != "" && this.input.password != "") {
            if (this.input.username == "admin" && this.input.password == "Mathilde") {
                this.$router.replace({ path: "/dashboardLayout" });
            } else {
                this.$alert("The username and/or password is incorrect",'Failed to log in','error');
            }
        } else {
            this.$alert("Both username and password must be present",'','warning');
        }
      }
    }
});
</script>