package alex.valker91.project_cuckoo.features.createnewclient

sealed class CreateClientEffect {
    object NavigateBackWithSuccess : CreateClientEffect()
}