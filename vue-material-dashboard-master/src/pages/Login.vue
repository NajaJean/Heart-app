<template>
    <form>
    <md-card class="md-layout-item md-small-size-75 md-size-50">
      <md-card-header data-background-color="blue">
        <h4 class="title">Log in</h4>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-75 md-size-75">
            <md-field>
              <label>Username</label>
              <md-input v-model="input.username" type="text"></md-input>
            </md-field>
            <md-field>
              <label>Password</label>
              <md-input v-model="input.password" type="password"></md-input>
            </md-field>
            <md-button class="md-raised md-info" @click="login()">Log In</md-button>
          </div>          
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
export default ({
    props:['authenticated'],
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
            if (this.input.username == "admin" && this.input.password == "mathilde") {
                //this.$emit("authenticated", true);
                this.$router.replace({ path: "/dashboard" });
            } else {
                //this.$emit("authenticated",false);
                console.log("The username and / or password is incorrect");
            }
        } else {
        console.log("A username and password must be present");
        }
      }
    }
});
</script>