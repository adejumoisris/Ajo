provider "aws" {
  region = local.region
}

data "aws_availability_zones" "available" {}

locals {
  name   = "ex-${basename(path.cwd)}"
  region = "eu-north-1"

  vpc_cidr = "10.0.0.0/16"
  azs      = slice(data.aws_availability_zones.available.names, 0, 3)

  tags = {
    name    = local.name
  }
}

### VPC Module
module "ajopay-vpc"{
  source = "terraform-aws-modules/vpc/aws"
  version = "5.1.0"

  #VPC Basic Details
  name = local.name
  cidr = local.vpc_cidr
  azs                 = ["eu-north-1a"]
  private_subnets     = ["10.0.1.0/24"]
  public_subnets      = ["10.0.102.0/24"]

  #Database Subnets
  database_subnets    = ["10.0.151.0/24")
  create_database_subnet_group           = true
  create_database_subnet_route_table     = true
  #create_database_internet_gateway_route = true

  ##NAT Gateway - Outbound flow for private subnet
  enable_nat_gateway = true
  single_nat_gateway = true
 }

