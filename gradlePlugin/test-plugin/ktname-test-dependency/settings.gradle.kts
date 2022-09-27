if (gradle.startParameter.projectDir?.name == "buildSrc") {
    gradle.startParameter.includedBuilds = ArrayList<File>()
}
