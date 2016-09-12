task :environment do
  errors = []
  file = File.open("#{ENV["PWD"]}/.env", 'w')
  %w(
    STORAGE_ACCOUNT_NAME
    CONTAINER_NAME
    KEY
    RESOURCE_GROUP_NAME
    ARM_SUBSCRIPTION_ID
    ARM_CLIENT_ID
    ARM_CLIENT_SECRET
    ARM_TENANT_ID
    ARM_ACCESS_KEY
    TF_VAR_subscription_id
    TF_VAR_client_id
    TF_VAR_client_secret
    TF_VAR_tenant_id
    TF_VAR_default_user
    TF_VAR_default_password
    TF_VAR_account_count
  ).each do |name|
    errors << name if ENV[name].nil?
    file.puts("#{name}=#{ENV[name]}") unless ENV[name].nil?
  end

  if errors.any?
    abort "One or more environment variables are empty: #{errors.join(", ")}"
  end
  file.close
end

task :configure => :environment do
  storage_account_name = ENV['STORAGE_ACCOUNT_NAME']
  container_name = ENV['CONTAINER_NAME']
  key = ENV['KEY']
  resource_group_name = ENV['RESOURCE_GROUP_NAME']
  sh %Q!cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest remote config -backend=azure -backend-config="storage_account_name=#{storage_account_name}" -backend-config="container_name=#{container_name}" -backend-config="key=#{key}" -backend-config="resource_group_name=#{resource_group_name}"!
end

task :pull => :configure do
  sh "cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest remote pull"
end

task :push => :configure do
  sh "cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest remote push"
end

task :plan do
  sh "cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest plan"
end

task :apply do
  sh "cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest apply"
end

task :destroy do
  sh "cd #{ENV['PWD']}; docker run --rm --name terraform --env-file ${PWD}/.env -v ${PWD}:/terraform -w /terraform hashicorp/terraform:latest destroy -force"
end

Rake::Task[:plan].enhance([:pull])

Rake::Task[:apply].enhance([:pull]) do
  Rake::Task[:push].invoke
end

Rake::Task[:destroy].enhance([:pull]) do
  Rake::Task[:push].invoke
end

task :default => :plan
