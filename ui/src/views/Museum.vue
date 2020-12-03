<template>
  <div class="container-fluid">
    <div class="row mb-5 mt-2">
      <Header />
    </div>
    <div class="row">
      <div class="col-md">
        <Map @toggle-article="toggleArticle" />
      </div>
<!--      <transition name="custom-classes-transition" enter-active-class="animate__animated animate__fadeInRight" leave-active-class="animate__animated animate__fadeOutRight">-->
        <router-view name="article" tag="div" class="col-md" />
<!--      </transition>-->
    </div>
    <div class="row mt-5 mb-5">
      <Footer />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Map from "@/components/Map";
import Footer from "@/components/Footer";

export default {
  name: "Museum",
  components: {
    Footer,
    Map,
    Header
  },
  data() {
    return {
      isArticleActive: false,
      contentColumns: ['Map'],
    }
  },
  methods: {
    toggleArticle(e) {
      this.isArticleActive = true;
      this.$router.push({
        name: 'incident',
        params: {
          incidentId: e.id
        }
      });
    }
  }
}
</script>

<style scoped>
</style>
